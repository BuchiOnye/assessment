package com.renmoney.assessment.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Column(unique = true, length = 256)
	private String name;
	
	@Column(length = 500)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<UserRoleMapper> roleMappers = new HashSet<UserRoleMapper>(0);


	
}
