package com.renmoney.assessment.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
	
	@NotBlank(message = "Kindly provide the name of this book")
	private String name;
	
	@NotBlank(message = "Kindly provide the a concise book description ")
	@Size(max = 200, min = 2)
	private String description;
	
	@NotBlank(message = "Book Registration Number cannot be null or empty")
	private String registrationNumber;
	
	@NotBlank(message = "Kindly enter the book's publisher")
	private String publisher;
	
	@NotBlank(message = "Kindly provide the author's full name")
	private String author;
	
	@NotBlank(message = "Kindly select a book genre")
	private String genre;

}
