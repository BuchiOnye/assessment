package com.buchi.assessment.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.buchi.assessment.model.enums.Gender;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String contactAddress;
	
	private String phoneNumber;
	
	private String password;
	
    @Enumerated(EnumType.STRING)
	private Gender gender;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserRoleMapper> roleMappers = new HashSet<UserRoleMapper>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<LendHistory> lendHistories;
	
}
