package org.prac.project.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginResponseDTO {

	private Long mno;
	
	private int resultNum;
	
}
