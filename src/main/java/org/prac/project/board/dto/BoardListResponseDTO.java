package org.prac.project.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponseDTO<D> {
	
	private List<D> boardList;
	
	private BoardPageResultDTO pageResult;

}
