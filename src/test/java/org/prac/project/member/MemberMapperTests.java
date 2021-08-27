package org.prac.project.member;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.prac.project.member.dto.MemberGetOneDTO;
import org.prac.project.member.dto.MemberInsertDTO;
import org.prac.project.member.dto.MemberUpdateReqDTO;
import org.prac.project.member.mapper.MemberMapper;
import org.prac.project.utils.AES256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void testInsert() {
		log.info("memberInsertTest");
		IntStream.rangeClosed(1, 1000).forEach(i -> {
			memberMapper.memberInsert(
					MemberInsertDTO.builder()
					.mid("testiD"+i)
					.mpassword("testPass"+i)
					.mname("testName"+i)
					.mphone("010"+i)
					.maddress("testAddress"+i)
					.mdeleted(false)
					.build()
					);
		});
		
	}
	
    @Test
    public void encDesTest() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    	AES256Util a256 = AES256Util.getInstance();
    	
        String id = "두루박";
        String memberNo = "비밀번호";
        String memberName = "거짓말이름";

        String enId = a256.encrypt(id);
        String enMemberNo = a256.encrypt(memberNo);
        String enMemberName = a256.encrypt(memberName);

        String desId = a256.decrypt(enId);
        String desMemberNo = a256.decrypt(enMemberNo);
        String desMemberName = a256.decrypt(enMemberName);

        log.info(enId);
        log.info(enMemberNo);
        log.info(enMemberName);
        
        log.info(desId);
        log.info(desMemberNo);
        log.info(desMemberName);
        
    }
	
	@Test
	public void testGetOne() {
		log.info("memberGetOne");
		log.info(memberMapper.getOneMember(2L));
	}
	
	@Test
	public void testSecurityInsert() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
    NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		log.info("testMemberSecurityInsert");
		AES256Util a256 = AES256Util.getInstance();
		
				memberMapper.memberInsert(
						MemberInsertDTO.builder()
						.mid("security testiD")
						.mpassword(a256.encrypt("securityTestPass"))
						.mname(a256.encrypt("securityTestName"))
						.mphone(a256.encrypt("01091626167"))
						.maddress(a256.encrypt("security testAddress"))
						.mdeleted(false)
						.build()
						);

	}
	
	@Test
	public void testSecurityGetOne() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
    NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		log.info("testMemberSecurityInsert");
		AES256Util a256 = AES256Util.getInstance();
		
		MemberGetOneDTO result = memberMapper.getOneMember(2009L);
		log.info("beforeEncryptResult"+result);
		
//		List<String> memberEncrpytList = null;
//		memberEncrpytList.add(a256.encrypt(result.getMpassword()));
//		memberEncrpytList.add(a256.encrypt(result.getMphone()));
//		memberEncrpytList.add(a256.encrypt(result.getMaddress()));
//		memberEncrpytList.add(a256.encrypt(result.getMphone()));
//		
//		memberEncrpytList.forEach(i -> {
//			result.setMpassword(i);
//			result.setMphone(i);
//			result.setMaddress(i);
//			result.setMphone(i);
//		});
		
		result.setMpassword(a256.decrypt(result.getMpassword()));
		result.setMphone(a256.decrypt(result.getMphone()));
		result.setMaddress(a256.decrypt(result.getMaddress()));
		result.setMname(a256.decrypt(result.getMname()));
		
		
		log.info("decryptResult"+result);
	}
	
	@Test
	public void testUpdate() {
		log.info("testUpdate");
		memberMapper.memberUpdate(
				MemberUpdateReqDTO.builder()
				.mno(2L)
				.mpassword("updateTestPassword")
				.mphone("01092435567")
				.maddress("updateTestMaddress")
				.mdeleted(true)
				.build()
				);
	}
	
	@Test
	public void testDelete() {
		log.info("testDelete");
		memberMapper.memberDelete(2L);
	}

}
