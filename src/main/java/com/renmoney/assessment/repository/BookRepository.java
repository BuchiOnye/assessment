package com.renmoney.assessment.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renmoney.assessment.dto.BookDto;
import com.renmoney.assessment.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query(value = "select id ,name, description, registration_number, publisher, author, genre from books "
			+ "where (lower(name) like '%:search' "
			+ "or lower(description) like '%:search' or "
			+ "lower(registration_number) like '%:search' or "
			+ "lower(author) like '%:search' or "
			+ "lower(genre) like '%:search')",
		countQuery = "SELECT count(id) FROM books "
				+ "where (lower(name) like '%:search' "
				+ "or lower(description) like '%:search' or "
				+ "lower(registration_number) like '%:search' or "
				+ "lower(author) like '%:search' or "
				+ "lower(genre) like '%:search')",
				nativeQuery = true)
	Page<BookDto> getBookBySearchParam(@Param("search") String searchParam, Pageable pageable);
	
	Optional<Book> findByRegistrationNumberAndAvailable(String registrationNumber, boolean isAvailable);
	

}
