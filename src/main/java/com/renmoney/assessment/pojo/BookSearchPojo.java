package com.renmoney.assessment.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchPojo {
	
	private String sortOrder;
	private String seachParam;
	private Integer pageNumber;
	private Integer pageSize;
	private String sortBy;

}
