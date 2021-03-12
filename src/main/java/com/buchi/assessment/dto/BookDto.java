package com.buchi.assessment.dto;

import com.buchi.assessment.model.enums.Genre;

public interface BookDto {

	String getName();
	String getDescription();
	String getRegistrationNumber();
	String getPublisher();
	String getAuthor();
	Genre getGenre();
	Long getId();

}
