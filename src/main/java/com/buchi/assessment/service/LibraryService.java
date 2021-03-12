package com.buchi.assessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.buchi.assessment.model.Book;
import com.buchi.assessment.pojo.BookRequest;
import com.buchi.assessment.pojo.BookSearchPojo;
import com.buchi.assessment.pojo.BookSearchResponse;

@Service
public class LibraryService {
	
	@Autowired
	LibraryActionProcessor libraryActionProcessor;
	
	private final static String SUCCESS = "SUCCESS";
	
	public ResponseEntity<BookSearchResponse> searchBookCollection(BookSearchPojo searchPojo){
		BookSearchResponse response = libraryActionProcessor.searchShelf(searchPojo);
		return new ResponseEntity<>(response, HttpStatus.OK );
	}
	
	public ResponseEntity<String> addBookToCollection(BookRequest bookRequest){
		libraryActionProcessor.addBook(bookRequest);
		return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED );
	}
	
	public ResponseEntity<String> updateBookDetails(Book book){
		libraryActionProcessor.updateBookDetails(book);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK );
	}
	
	public ResponseEntity<String> deleteBook(Long id){
		libraryActionProcessor.removeBook(id);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	public ResponseEntity<Book> borrowBook(String registrationNumber, String username){
	Book book = libraryActionProcessor.lendBooks(registrationNumber, username);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	

}
