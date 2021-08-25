package org.prac.project.member;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.prac.project.member.dto.MemberInsertDTO;
import org.prac.project.member.dto.MemberUpdateReqDTO;
import org.prac.project.member.mapper.MemberMapper;
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
	public void testGetOne() {
		log.info("memberGetOne");
		log.info(memberMapper.getOneMember(2L));
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
