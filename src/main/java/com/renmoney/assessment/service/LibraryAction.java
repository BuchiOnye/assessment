package com.renmoney.assessment.service;

import com.renmoney.assessment.model.Book;
import com.renmoney.assessment.pojo.BookRequest;
import com.renmoney.assessment.pojo.BookSearchPojo;
import com.renmoney.assessment.pojo.BookSearchResponse;

public interface LibraryAction {
	public void addBook(BookRequest bookRequest);
	
	public void updateBookDetails(Book bookRequest);
	
	public void removeBook(Long id);
	
	public Book lendBooks(String registrationNumber, String username);
	
	public BookSearchResponse searchShelf(BookSearchPojo bookSearchPojo);
	

}
