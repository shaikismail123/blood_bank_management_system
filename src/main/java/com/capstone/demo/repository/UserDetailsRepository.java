package com.capstone.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.demo.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

}
