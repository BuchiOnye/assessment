package com.buchi.assessment.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.buchi.assessment.dto.BookDto;
import com.buchi.assessment.model.Book;
import com.buchi.assessment.model.enums.Genre;
import com.buchi.assessment.pojo.BookRequest;
import com.buchi.assessment.pojo.BookSearchPojo;
import com.buchi.assessment.pojo.BookSearchResponse;
import com.buchi.assessment.repository.BookRepository;
import com.buchi.assessment.repository.LendHistoryRepository;
import com.buchi.assessment.repository.UserReository;
import com.buchi.assessment.service.LibraryActionProcessor;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@RunWith(MockitoJUnitRunner.class)
public class LibraryActionProcessorTest {

	@Mock
	BookRepository bookRepository;

	@Mock
	UserReository userRepository;

	@Mock
	LendHistoryRepository lendHistoryRepository;

	@InjectMocks
	LibraryActionProcessor libraryActionProcessor;

	private Book book;
	private BookRequest bookRequest;
	Page<BookDto> pagedBooks;
	BookSearchPojo bookSearchPojo;
	Pageable filterParam;

	@Before
	public void setup() {
		book = Book.builder().active(true)
				.author("Test test")
				.available(true).description("App Test").genre(Genre.ACTION).id(1L).name("Action Test")
				.publisher("Tailleo Inc").registrationNumber("TEST1234").build();

		bookRequest = BookRequest.builder().author("Test test")
				.description("App Test").genre(Genre.ACTION.name()).name("Action Test")
				.publisher("Tailleo Inc").registrationNumber("TEST1234").build();

		filterParam = PageRequest.of(1, 1, Sort.by("name").ascending());

		pagedBooks = new PageImpl<>(new ArrayList<BookDto>(), filterParam, 1);
		bookSearchPojo = BookSearchPojo.builder().pageNumber(1).pageSize(10).seachParam("").sortOrder("ascending").build();
	}


	@Test
	public void createBookTest()  {
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		Book result = libraryActionProcessor.creatBook(bookRequest, Genre.ACTION);
		assertEquals(true, result.isAvailable());

	}

	@Test
	public void searchBookTest()  {
		when(bookRepository.getBookBySearchParam(anyString(), any(Pageable.class))).thenReturn(pagedBooks);
		BookSearchResponse result = libraryActionProcessor.searchShelf(bookSearchPojo);
		assertEquals(1, result.getPageNumber());
	}
	
	@Test
    public void deleteBookTest() {
        doNothing().when(bookRepository).deleteById(any(Long.class));
        bookRepository.deleteById(1L);
        verify(bookRepository, atLeastOnce()).deleteById(any(Long.class));

    }
}
