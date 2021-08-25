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
public class MemberInsertDTO {
	
	private String mid;
	
	private String mpassword;
	
	private String mname;
	
	private String mphone;
	
	private String maddress;
	
	private boolean mdeleted;
	
	private LocalDateTime mregdate;
	
	private LocalDateTime mmoddate;

}
