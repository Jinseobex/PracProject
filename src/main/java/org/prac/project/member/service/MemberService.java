package org.prac.project.member.service;

import org.prac.project.member.dto.MemberGetOneDTO;
import org.prac.project.member.dto.MemberInsertDTO;
import org.prac.project.member.dto.MemberUpdateReqDTO;

public interface MemberService {

	public void memberInsert(MemberInsertDTO dto);
	
	public MemberGetOneDTO getOneMember(Long mno);
	
	public void memberUpdate(MemberUpdateReqDTO dto);
	
	public void memberDelete(Long mno);
}
