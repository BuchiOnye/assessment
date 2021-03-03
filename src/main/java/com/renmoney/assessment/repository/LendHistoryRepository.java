package com.renmoney.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renmoney.assessment.model.LendHistory;

@Repository
public interface LendHistoryRepository extends JpaRepository<LendHistory, Long> {

}
