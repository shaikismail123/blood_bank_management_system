package com.capstone.demo.service;

import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.exception.UserNotFoundException;

public interface MyUserDetailsService {

	public boolean insertUserDetails(UserDetailsDto userDetailsDto);

	public UserDetailsDto userDetailsById(Long id) throws UserNotFoundException;

	public boolean deleteDonerDetails(Long id) throws UserNotFoundException;

}
