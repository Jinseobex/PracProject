package org.prac.project.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardSearchRequestDTO {
	
	/* 페이지, 페이지 사이즈, 검색 키워드/타입(제목, 내용, 작성자)을 담아 전달하는 DTO */
	@Builder.Default
	private int page = 1;
	@Builder.Default
	private int size = 10;
	
	private String type;
	private String keyword;
	
	public int getSkip() {
		return (page-1) * size;
	}
	
	/* 검색어 입력 및 리턴 */
	public String[] getArr() {
		if(keyword == null) {
			return null;
		} else {
			return type.split("");
		}
	}

}
