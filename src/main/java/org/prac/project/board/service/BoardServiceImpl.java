package org.prac.project.board.service;

import java.util.List;

import org.prac.project.board.dto.BoardGetOneDTO;
import org.prac.project.board.dto.BoardInsertDTO;
import org.prac.project.board.dto.BoardListResponseDTO;
import org.prac.project.board.dto.BoardPageResultDTO;
import org.prac.project.board.dto.BoardSearchRequestDTO;
import org.prac.project.board.dto.BoardSearchResponseDTO;
import org.prac.project.board.dto.BoardUpdateRequestDTO;
import org.prac.project.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Override
	public void boardInsert(BoardInsertDTO dto) {
		boardMapper.boardInsert(dto);
	}

	@Override
	public BoardGetOneDTO boardSelect(Long bno) {
		return boardMapper.boardSelect(bno);
	}

	@Override
	public void boardUpdate(BoardUpdateRequestDTO dto) {
		boardMapper.boardUpdate(dto);
	}

	@Override
	public void boardDelete(Long bno) {
		boardMapper.boardDelete(bno);
	}

	@Override
	public BoardListResponseDTO<BoardSearchResponseDTO> getPageList(BoardSearchRequestDTO dto) {
		List<BoardSearchResponseDTO> result = boardMapper.getPageList(dto);
		log.info("getPageList"+result);
		
		int totalCnt = boardMapper.getPageList(dto).get(0).getTotalCnt();
		
		boardMapper.getPageList(dto).forEach(boardMapper -> {
			log.info(boardMapper);
		});
		
		BoardPageResultDTO dtos = new BoardPageResultDTO(dto.getPage(), dto.getSize(), totalCnt);
		log.info("testGetPageList Result"+dtos);
		
		return BoardListResponseDTO.<BoardSearchResponseDTO>builder()
				.boardSearchResponseDTO(result)
				.boardPageResultDTO(dtos)
				.build();
	}


}
