package org.prac.project.board;


import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.prac.project.board.dto.BoardInsertDTO;
import org.prac.project.board.dto.BoardPageResultDTO;
import org.prac.project.board.dto.BoardSearchRequestDTO;
import org.prac.project.board.dto.BoardUpdateRequestDTO;
import org.prac.project.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testInsert() {
		log.info("boardInsertTest");
		IntStream.rangeClosed(1, 1000).forEach(i -> {
			boardMapper.boardInsert(
					BoardInsertDTO.builder()
					.btitle("testTitle"+i)
					.bcontent("testContent"+i)
					.bdeleted(false)
					.mno((long)i)
					.build()
					);
		});
		
	}
	
	@Test
	public void testGetOne() {
		log.info("RESULT ------------------"+boardMapper.boardSelect(5L));
	}
	
	@Test
	public void testUpdate() {
		log.info("boardUpdateTest");
			boardMapper.boardUpdate(
					BoardUpdateRequestDTO.builder()
					.btitle("UPDATE_TEST1")
					.bcontent("UPDATE_TEST1")
					.bdeleted(true)
					.bno(4L)
					.build()
					);
	}
	
	@Test
	public void testDelete() {
		log.info("boardDeleteTest");
		boardMapper.boardDelete(5L);
	}
	
	@Test
	public void testGetPageList() {
		log.info("testGetPageList");
		
		BoardSearchRequestDTO dto = BoardSearchRequestDTO.builder()
				.page(1)
				.size(10)
				.type("c")
				.keyword("사탕")
				.build();  
		
		int totalCnt = boardMapper.getPageList(dto).get(0).getTotalCnt();
		
		boardMapper.getPageList(dto).forEach(boardMapper -> {
			log.info(boardMapper);
		});
		
		BoardPageResultDTO dtos = new BoardPageResultDTO(dto.getPage(), dto.getSize(), totalCnt);
		log.info("testGetPageList Result"+dtos);
		
	}

}
