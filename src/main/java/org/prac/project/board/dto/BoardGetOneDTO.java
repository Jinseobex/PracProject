package org.prac.project.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardGetOneDTO {

	private String btitle;
	private String bcontent;
	private boolean bdeleted;
	private LocalDateTime bmoddate;
	private Long mno;
	private String mid;
	
}
