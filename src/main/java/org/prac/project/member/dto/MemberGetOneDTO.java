package org.prac.project.member.dto;

import java.time.LocalDateTime;

import org.prac.project.utils.AES256Util;

import lombok.Data;

@Data
public class MemberGetOneDTO {

	private String mid;
	private String mpassword;
	private String mname;
	private String mphone;
	private String maddress;
	private boolean mdeleted;
	private LocalDateTime mregdate;
	private LocalDateTime mmoddate;
	
}
