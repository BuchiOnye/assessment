package com.renmoney.assessment.pojo;

import java.util.List;

import com.renmoney.assessment.dto.BookDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSearchResponse {
	
	private List<BookDto> books;
	private Integer totalElementCount;
	private int pageNumber;
	private Integer totalPageCount;
	

}
