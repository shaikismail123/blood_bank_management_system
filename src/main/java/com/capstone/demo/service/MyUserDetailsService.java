package com.capstone.demo.service;

import com.capstone.demo.dto.UserDetailsDto;

public interface MyUserDetailsService {

	public boolean insertUserDetails(UserDetailsDto userDetailsDto);

	public UserDetailsDto userDetailsById(Long id);

	public boolean deleteDonerDetails(Long id);

}
