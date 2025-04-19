package com.capstone.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.demo.entity.RequesterDetails;

public interface RequesterDetailsRepository extends JpaRepository<RequesterDetails, Long> {

	@Query(value = "select * from blood_request where status='pending'", nativeQuery = true)
	public List<RequesterDetails> getAllRequesterForApproval();

	public List<RequesterDetails> findByDonarId(Long donarId);

}
