package com.capstone.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.demo.entity.RequesterDetails;

public interface RequesterDetailsRepository extends JpaRepository<RequesterDetails, Long>{
	

}
