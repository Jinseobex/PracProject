package org.prac.project.board.controller;

import java.util.List;

import org.prac.project.board.dto.BoardGetOneDTO;
import org.prac.project.board.dto.BoardInsertDTO;
import org.prac.project.board.dto.BoardListResponseDTO;
import org.prac.project.board.dto.BoardSearchRequestDTO;
import org.prac.project.board.dto.BoardSearchResponseDTO;
import org.prac.project.board.dto.BoardUpdateRequestDTO;
import org.prac.project.board.service.BoardService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
@Log4j
public class BoardController {
	
	private final BoardService boardService;

	@PostMapping(value= "/insert", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> boardInsert(@RequestBody BoardInsertDTO dto) {
		log.info("====================== starting boardInsert");
		boardService.boardInsert(dto);
		return ResponseEntity.ok("입력 완료");
	}
	
	@DeleteMapping("/{bno}")
	public ResponseEntity<String> boardDelete(@PathVariable Long bno) {
		log.info("====================== starting boardDelete");
		boardService.boardDelete(bno);
		return ResponseEntity.ok("삭제 완료");
	}
	
	@GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<BoardGetOneDTO> boardGetOne(@PathVariable Long bno) {
		log.info("====================== starting boardGetOne");
		BoardGetOneDTO result = boardService.boardSelect(bno);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{bno}")
	public ResponseEntity<Long> boardUpdate(@RequestBody BoardUpdateRequestDTO dto) {
		log.info("====================== starting boardUpdate");
		if(dto != null) {
			boardService.boardUpdate(dto);
			return ResponseEntity.ok(200L);
		}
		return null;
	}
	
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<BoardListResponseDTO<?>> boardGetList(BoardSearchRequestDTO dto) {
		log.info("====================== starting boardGetList");
		log.info("=========================== dto"+dto);
		BoardListResponseDTO<BoardSearchResponseDTO> data = boardService.getPageList(dto);
		log.info("=========================== data"+data);
		return ResponseEntity.ok(data);
	}

}
