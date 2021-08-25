package org.prac.project.member.mapper;

import org.prac.project.member.dto.MemberGetOneDTO;
import org.prac.project.member.dto.MemberInsertDTO;
import org.prac.project.member.dto.MemberUpdateReqDTO;

public interface MemberMapper {

	void memberInsert(MemberInsertDTO dto);
	
	MemberGetOneDTO getOneMember(Long mno);
	
	void memberUpdate(MemberUpdateReqDTO dto);
	
	void memberDelete(Long mno);
	
}
