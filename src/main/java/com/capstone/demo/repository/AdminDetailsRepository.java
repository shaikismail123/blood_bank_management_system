package com.capstone.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.demo.entity.AdminOperations;

import jakarta.transaction.Transactional;

public interface AdminDetailsRepository extends JpaRepository<AdminOperations, Long> {

	@Transactional
	@Query(value = "delete from admin_operations where requester_id= ?1", nativeQuery = true)
	void deleteTheRequesterIdFromAdmin(Long id);

}
