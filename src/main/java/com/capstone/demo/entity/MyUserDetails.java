package com.capstone.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "my_users_details")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column
	@NotBlank
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String bloodGroup;

	@Column
	private String city;

	@Email
	@Column
	private String email;

	@Column
	private String phoneNumber;

	@Column
	@NotBlank
	private String userType; // DONOR, REQUESTOR, ADMIN

	@NotBlank
	@Column
	private String passwordHash;

}
