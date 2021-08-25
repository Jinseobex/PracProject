package org.prac.project.board.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class BoardPageResultDTO {
	
	/* 페이지 계산 DTO, 입력 받은 페이지, 사이즈, 총 갯수를 통해 이전/다음 버튼과 시작/끝, 페이지 리스트를 계산하여 리턴 */
	private boolean prev, next;
	private int page, size, totalCount;
	private int start, end;
	private List<Integer> pageNumList;
	
	public BoardPageResultDTO(int page, int size, int totalCount) {
		
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;

        int totalPage = (int) (Math.ceil(totalCount/(double)size));
        int tempEnd = (int) (Math.ceil(page/10.0) * 10);
        
        this.start = tempEnd - 9;
        this.end = totalPage > tempEnd ? tempEnd : totalPage;
        
        prev = start > 1;
        next = totalPage > tempEnd;
        pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
		
	}

}
