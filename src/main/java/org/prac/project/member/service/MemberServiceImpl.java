package org.prac.project.member.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.prac.project.member.dto.MemberGetOneDTO;
import org.prac.project.member.dto.MemberInsertDTO;
import org.prac.project.member.dto.MemberLoginDTO;
import org.prac.project.member.dto.MemberLoginResponseDTO;
import org.prac.project.member.dto.MemberUpdateReqDTO;
import org.prac.project.member.mapper.MemberMapper;
import org.prac.project.utils.AES256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	private AES256Util a256 = AES256Util.getInstance();
	
	@Override
	public MemberLoginResponseDTO memberLogin(MemberLoginDTO dto) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		dto.setMpassword(a256.encrypt(dto.getMpassword()));

		log.info("들어가기 전 요청 dto"+dto);
		log.info("dtoRes : "+memberMapper.memberLogin(dto));
		log.info("mno"+memberMapper.memberLogin(dto).getMno());

		return 	MemberLoginResponseDTO.builder()
				.mno(memberMapper.memberLogin(dto).getMno())
				.resultNum(memberMapper.memberLogin(dto).getResultNum())
				.build();
	}
	
	@Override
	public void memberInsert(MemberInsertDTO dto) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		dto.setMname(a256.encrypt(dto.getMname()));
		dto.setMpassword(a256.encrypt(dto.getMpassword()));
		dto.setMphone(a256.encrypt(dto.getMphone()));
		dto.setMaddress(a256.encrypt(dto.getMaddress()));
		memberMapper.memberInsert(dto);
	}
	
	@Override
	public int idCheck(String mid) {
		return memberMapper.idCheck(mid);
	}

	@Override
	public MemberGetOneDTO getOneMember(Long mno) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		MemberGetOneDTO result = memberMapper.getOneMember(mno);
		result.setMpassword(a256.decrypt(result.getMpassword()));
		result.setMphone(a256.decrypt(result.getMphone()));
		result.setMaddress(a256.decrypt(result.getMaddress()));
		result.setMname(a256.decrypt(result.getMname()));
		return result;
	}

	@Override
	public void memberUpdate(MemberUpdateReqDTO dto) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		dto.setMpassword(a256.encrypt(dto.getMpassword()));
		dto.setMphone(a256.encrypt(dto.getMphone()));
		dto.setMaddress(a256.encrypt(dto.getMaddress()));
		memberMapper.memberUpdate(dto);
	}
	
	@Override
	public void memberDelete(Long mno) {
		memberMapper.memberDelete(mno);
	}

}
