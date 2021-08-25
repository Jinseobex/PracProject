package org.prac.project.member.service;

import java.util.Optional;

import org.prac.project.member.dto.MemberGetOneDTO;
import org.prac.project.member.dto.MemberInsertDTO;
import org.prac.project.member.dto.MemberUpdateReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Setter(onMethod_ = @Autowired)
	private org.prac.project.member.mapper.MemberMapper memberMapper;
	
	@Override
	public void memberInsert(MemberInsertDTO dto) {
		memberMapper.memberInsert(dto);
	}

	@Override
	public MemberGetOneDTO getOneMember(Long mno) {
		return memberMapper.getOneMember(mno);
	}

	@Override
	public void memberUpdate(MemberUpdateReqDTO dto) {
		memberMapper.memberUpdate(dto);
	}
	
	@Override
	public void memberDelete(Long mno) {
		memberMapper.memberDelete(mno);
	}

}
