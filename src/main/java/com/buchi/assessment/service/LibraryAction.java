package com.buchi.assessment.service;

import com.buchi.assessment.model.Book;
import com.buchi.assessment.pojo.BookRequest;
import com.buchi.assessment.pojo.BookSearchPojo;
import com.buchi.assessment.pojo.BookSearchResponse;

public interface LibraryAction {
	public void addBook(BookRequest bookRequest);
	
	public void updateBookDetails(Book bookRequest);
	
	public void removeBook(Long id);
	
	public Book lendBooks(String registrationNumber, String username);
	
	public BookSearchResponse searchShelf(BookSearchPojo bookSearchPojo);
	

}
