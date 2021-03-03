package com.renmoney.assessment.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.renmoney.assessment.model.enums.Genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "books",uniqueConstraints = @UniqueConstraint(columnNames = {"registrationNumber"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Book extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(length = 256)
	private String name;
	
	@Column(length = 256)
	private String description;
	
	private String registrationNumber;
	
	private String publisher;
	
	private String author;
	
    @Enumerated(EnumType.STRING)
	private Genre genre;
    
	private boolean available;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    @Builder.Default
    private Set<LendHistory> lendHistories = new HashSet<LendHistory>(0);


}
