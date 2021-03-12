package com.buchi.assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buchi.assessment.model.User;

@Repository
public interface UserReository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
}
