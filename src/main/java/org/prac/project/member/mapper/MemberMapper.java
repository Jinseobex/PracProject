package org.prac.project.member.mapper;

import org.prac.project.member.dto.MemberGetOneDTO;
import org.prac.project.member.dto.MemberInsertDTO;
import org.prac.project.member.dto.MemberLoginDTO;
import org.prac.project.member.dto.MemberLoginResponseDTO;
import org.prac.project.member.dto.MemberUpdateReqDTO;

public interface MemberMapper {
	
	MemberLoginResponseDTO memberLogin(MemberLoginDTO dto);

	void memberInsert(MemberInsertDTO dto);
	
	int idCheck(String mid);

	MemberGetOneDTO getOneMember(Long mno);
	
	void memberUpdate(MemberUpdateReqDTO dto);
	
	void memberDelete(Long mno);
	
}
