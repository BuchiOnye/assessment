package com.buchi.assessment.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.buchi.assessment.dto.BookDto;
import com.buchi.assessment.exception.BadRequestException;
import com.buchi.assessment.exception.DuplicateResultException;
import com.buchi.assessment.exception.NotFoundException;
import com.buchi.assessment.exception.UnauthorizedException;
import com.buchi.assessment.model.Book;
import com.buchi.assessment.model.LendHistory;
import com.buchi.assessment.model.User;
import com.buchi.assessment.model.enums.Genre;
import com.buchi.assessment.pojo.BookRequest;
import com.buchi.assessment.pojo.BookSearchPojo;
import com.buchi.assessment.pojo.BookSearchResponse;
import com.buchi.assessment.repository.BookRepository;
import com.buchi.assessment.repository.LendHistoryRepository;
import com.buchi.assessment.repository.UserReository;

@Component
public class LibraryActionProcessor implements LibraryAction {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserReository userRepository;

	@Autowired
	LendHistoryRepository lendHistoryRepository;

	@Override
	public void addBook(BookRequest bookRequest) {
		Genre genre = Genre.findGenreByName(bookRequest.getGenre());
		if(genre == null) {
			throw new BadRequestException("400", "Unknown book genre");
		}
		
		creatBook(bookRequest, genre);
	}

	public Book creatBook(BookRequest bookRequest, Genre genre) {
		Book book = Book.builder().author(bookRequest.getAuthor()).available(true)
				.description(bookRequest.getDescription()).genre(genre).publisher(bookRequest.getPublisher())
				.name(bookRequest.getName()).registrationNumber(bookRequest.getRegistrationNumber()).build();

		try {
			return bookRepository.save(book); 
		} catch (DataIntegrityViolationException exception) {
			throw new DuplicateResultException("417", "Oops!! A book already exists with registration number : "+bookRequest.getRegistrationNumber());
			
		}
	}

	@Override
	public void updateBookDetails(Book bookRequest) {
		if(bookRequest.getId() == null) {
			throw new BadRequestException("400", "Kindly provide a valid book object");
		}

		bookRepository.save(bookRequest);

	}

	@Override
	public void removeBook(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public Book lendBooks(String registrationNumber, String username) {
		
		Optional<User> user = userRepository.findByEmail(username);
		if(!user.isPresent()) {
			throw new UnauthorizedException("401", "No user exists with id : "+username);
		}

		Optional<Book> book = bookRepository.findByRegistrationNumberAndAvailable(registrationNumber, true);

		if(!book.isPresent()) {
			throw new NotFoundException("404", "No available book present with registration number: ".concat(registrationNumber));
		}

		book.get().setAvailable(false);
		Book selectedBook = bookRepository.save(book.get());

		saveLendHistory(user, selectedBook);

		return selectedBook;
	}

	private void saveLendHistory(Optional<User> user, Book selectedBook) {
		LendHistory lendHistory = LendHistory.builder()
				.lastUpdatedBy(user.get().getLastName())
				.createdBy(user.get().getLastName())
				.paid(true).user(user.get()).book(selectedBook).build();

		lendHistoryRepository.save(lendHistory);
	}

	@Override
	public BookSearchResponse searchShelf(BookSearchPojo bookSearchPojo) {
		Sort sort = retrieveSortObject(bookSearchPojo);
		
		Pageable filterParam = PageRequest.of(bookSearchPojo.getPageNumber(), bookSearchPojo.getPageSize(), sort);
		String searchParam = StringUtils.isBlank(bookSearchPojo.getSeachParam()) ? "" : bookSearchPojo.getSeachParam();
		Page<BookDto> books = bookRepository.getBookBySearchParam(searchParam.toLowerCase(), filterParam);

		return BookSearchResponse.builder()
				.books(books.getContent())
				.totalPageCount(books.getTotalPages())
				.totalElementCount(books.getNumberOfElements())
				.pageNumber(bookSearchPojo.getPageNumber()).build();
	}

	private Sort retrieveSortObject(BookSearchPojo bookSearchPojo) {
		Sort sort;
		switch (bookSearchPojo.getSortOrder()) {
		case "ASCENDING":
			sort = Sort.by("name").ascending();
			break;
		case "DECENDING":
			sort = Sort.by("name").descending();
			break;
		default:
			sort = Sort.by("name");
		}
		return sort;
	}

}
