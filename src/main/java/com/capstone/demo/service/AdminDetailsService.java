package com.capstone.demo.service;

import java.util.List;

import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.AdminOperations;
import com.capstone.demo.entity.RequesterDetails;

public interface AdminDetailsService {

	public List<UserDetailsDto> getAllDonarsForAdmin();

	public List<RequesterDetailsDto> getAllRequesterForApproval();

//	public String updateRequsterDetaisByAdmin(RequesterDetails reqeuserDetails);

	public String saveAdminDetails(AdminOperations adminOperations);
}
