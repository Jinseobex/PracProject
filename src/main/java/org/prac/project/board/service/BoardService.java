package org.prac.project.board.service;

import org.prac.project.board.dto.BoardGetOneDTO;
import org.prac.project.board.dto.BoardInsertDTO;
import org.prac.project.board.dto.BoardListResponseDTO;
import org.prac.project.board.dto.BoardSearchRequestDTO;
import org.prac.project.board.dto.BoardSearchResponseDTO;
import org.prac.project.board.dto.BoardUpdateRequestDTO;

public interface BoardService {

	void boardInsert(BoardInsertDTO dto);
	
	BoardGetOneDTO boardSelect(Long bno);
	
	void boardUpdate(BoardUpdateRequestDTO dto);
	
	void boardDelete(Long bno);
	
	BoardListResponseDTO<BoardSearchResponseDTO> getPageList(BoardSearchRequestDTO dto);
}
