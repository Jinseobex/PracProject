package org.prac.project.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardSearchResponseDTO {
	
	private Long bno;
	private String btitle, bcontent;
	private boolean bdeleted;
	private LocalDateTime bmoddate;
	
	private Long mno;
	private String mid;
	
	private int totalCnt;
	
}
