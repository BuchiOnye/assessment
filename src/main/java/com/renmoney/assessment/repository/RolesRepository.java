package com.renmoney.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.renmoney.assessment.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
	
	@Query(value = "select rl.name from roles rl inner join user_role_mapper urm on urm.role = rl.id  where  urm.user = :user_id", nativeQuery = true)
	List<String> getUserRoles(@Param("user_id") Long userId);

}
