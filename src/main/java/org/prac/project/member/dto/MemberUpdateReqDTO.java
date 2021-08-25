package org.prac.project.member.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateReqDTO {
	
	private Long mno;

	private String mpassword;
	
	private String mphone;
	
	private String maddress;
	
	private boolean mdeleted;
	
	private LocalDateTime mmoddate;
	
}
