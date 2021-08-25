package org.prac.project.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardInsertDTO {

	private String btitle;
	private String bcontent;
	private boolean bdeleted;
	private LocalDateTime bregdate;
	private LocalDateTime bmoddate;
	private Long mno;
	
}
