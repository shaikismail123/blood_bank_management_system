package com.capstone.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.demo.entity.RequesterDetails;

public interface RequesterDetailsRepository extends JpaRepository<RequesterDetails, Long> {

	@Query(value = "select * from requester_details where status='PENDING'", nativeQuery = true)
	public List<RequesterDetails> getAllRequesterForApproval();

	@Query(value = "select * from requester_details where donar_id=?1 and status='APPROVED'", nativeQuery = true)
	public List<RequesterDetails> getDonarDetails(Long donarId);

}
