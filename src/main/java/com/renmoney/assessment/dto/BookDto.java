package com.renmoney.assessment.dto;

import com.renmoney.assessment.model.enums.Genre;

public interface BookDto {

	String getName();
	String getDescription();
	String getRegistrationNumber();
	String getPublisher();
	String getAuthor();
	Genre getGenre();
	Long getId();

}
