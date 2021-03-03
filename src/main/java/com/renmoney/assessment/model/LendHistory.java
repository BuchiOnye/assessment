package com.renmoney.assessment.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LendHistory extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

    private boolean paid;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "return_date", length = 13)
    private Date returnDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book", nullable = true)
    private Book book;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "borrower", nullable = true)
    private User user;
  
}
