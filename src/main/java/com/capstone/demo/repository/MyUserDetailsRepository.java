package com.capstone.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.demo.entity.MyUserDetails;

public interface MyUserDetailsRepository extends JpaRepository<MyUserDetails, Long> {

	@Query(value = "select * from users_details where user_type='DONAR' and blood_status_added_or_not='NO'", nativeQuery = true)
	public List<MyUserDetails> getDonarsToAddBloodCount();

}
