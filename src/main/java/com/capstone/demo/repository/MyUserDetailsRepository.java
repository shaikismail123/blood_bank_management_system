package com.capstone.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.demo.entity.MyUserDetails;

public interface MyUserDetailsRepository extends JpaRepository<MyUserDetails, Long> {

	@Query(value = "select * from my_users_details where user_type='Donar'", nativeQuery = true)
	public List<MyUserDetails> getAllDonarsForAdmin();

	@Query(value = "select * from my_users_details where user_type='Requester'", nativeQuery = true)
	public List<MyUserDetails> getAllRequestersForAdmin();

	public MyUserDetails findByEmail(String username);

	@Query(value = "select distinct email from my_users_details where user_type='Admin'", nativeQuery = true)
	public String getAdminEmail();

}
