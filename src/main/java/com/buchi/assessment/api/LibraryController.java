package com.buchi.assessment.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buchi.assessment.model.Book;
import com.buchi.assessment.pojo.BookRequest;
import com.buchi.assessment.pojo.BookSearchPojo;
import com.buchi.assessment.pojo.BookSearchResponse;
import com.buchi.assessment.service.LibraryService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("library/books")
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	@GetMapping
	public ResponseEntity<BookSearchResponse> getBookCollection(
			@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "") String seachParam,
			@RequestParam(defaultValue = "DESCENDING") String sortOrder,
			@RequestParam(defaultValue = "id") String sortBy){

		BookSearchPojo searchPojo = BookSearchPojo.builder().
				pageNumber(pageNumber).pageSize(pageSize).seachParam(seachParam)
				.sortBy(sortBy).sortOrder(sortOrder).build();
		
		return libraryService.searchBookCollection(searchPojo);
	}

	@PostMapping
	public ResponseEntity<String> addToCollection(@Valid @RequestBody BookRequest bookRequest){
		return libraryService.addBookToCollection(bookRequest);
	}


	@PatchMapping
	public ResponseEntity<String> updateBookDetails(@NotNull @RequestBody Book bookRequest){
		return libraryService.updateBookDetails(bookRequest);
	}


	@DeleteMapping
	public ResponseEntity<String> deleteBookFromCollection(@NotNull  Long id){
		return libraryService.deleteBook(id);
	}

	@GetMapping(value = "/borrow/{registrationNo}")
	public ResponseEntity<?> getLendHistory(@PathVariable String registrationNo, @ApiIgnore OAuth2Authentication oAuth2Authentication){
		return libraryService.borrowBook(registrationNo, oAuth2Authentication.getName());

	}
}
