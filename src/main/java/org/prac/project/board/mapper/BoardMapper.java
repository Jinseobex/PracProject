package org.prac.project.board.mapper;

import java.util.List;

import org.prac.project.board.dto.BoardGetOneDTO;
import org.prac.project.board.dto.BoardInsertDTO;
import org.prac.project.board.dto.BoardSearchRequestDTO;
import org.prac.project.board.dto.BoardSearchResponseDTO;
import org.prac.project.board.dto.BoardUpdateRequestDTO;

public interface BoardMapper {
	
	void boardInsert(BoardInsertDTO dto);
	
	BoardGetOneDTO boardSelect(Long bno);
	
	void boardUpdate(BoardUpdateRequestDTO dto);
	
	void boardDelete(Long bno);
	
	List<BoardSearchResponseDTO> getPageList(BoardSearchRequestDTO dto);

}
