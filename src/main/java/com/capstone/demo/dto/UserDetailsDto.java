package com.capstone.demo.dto;

public class UserDetailsDto {

	private Long userId;

	private String firstName;

	private String lastName;

	private String bloodGroup;

	private String city;

	private String email;

	private String phoneNumber;

	private String userType; // DONOR, REQUESTOR, ADMIN

	private String passwordHash;

	public UserDetailsDto() {
	}

	public UserDetailsDto(Long userId, String firstName, String lastName, String bloodGroup, String city, String email,
			String phoneNumber, String userType, String passwordHash) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bloodGroup = bloodGroup;
		this.city = city;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userType = userType;
		this.passwordHash = passwordHash;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public String toString() {
		return "UserDetailsDto [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", bloodGroup=" + bloodGroup + ", city=" + city + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", userType=" + userType + ", passwordHash=" + passwordHash + "]";
	}

}
