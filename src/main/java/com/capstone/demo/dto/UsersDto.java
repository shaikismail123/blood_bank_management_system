package com.capstone.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

	private Long userId;

	private String firstName;

	private String lastName;

	private String bloodGroup;

	private String city;

	private String phoneNumber;
}
