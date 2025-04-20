package com.capstone.demo.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Entity
@Table(name = "my_users_details")
@Builder
public class MyUserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private String firstName;

	private String lastName;

	private String bloodGroup;

	private String city;
	@Email
	private String email;

	private String phoneNumber;

	private String userType; // DONOR, REQUESTOR, ADMIN

	@NotBlank
	private String passwordHash;
	
	private String bloodStatusAddedOrNot;
	

	public MyUserDetails() {

	}


	public MyUserDetails(Long userId, String firstName, String lastName, String bloodGroup, String city,
			@Email String email, String phoneNumber, String userType, @NotBlank String passwordHash,
			String bloodStatusAddedOrNot) {
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
		this.bloodStatusAddedOrNot = bloodStatusAddedOrNot;
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


	public String getBloodStatusAddedOrNot() {
		return bloodStatusAddedOrNot;
	}


	public void setBloodStatusAddedOrNot(String bloodStatusAddedOrNot) {
		this.bloodStatusAddedOrNot = bloodStatusAddedOrNot;
	}


	@Override
	public int hashCode() {
		return Objects.hash(bloodGroup, bloodStatusAddedOrNot, city, email, firstName, lastName, passwordHash,
				phoneNumber, userId, userType);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyUserDetails other = (MyUserDetails) obj;
		return Objects.equals(bloodGroup, other.bloodGroup)
				&& Objects.equals(bloodStatusAddedOrNot, other.bloodStatusAddedOrNot)
				&& Objects.equals(city, other.city) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(passwordHash, other.passwordHash) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(userId, other.userId) && Objects.equals(userType, other.userType);
	}


	@Override
	public String toString() {
		return "MyUserDetails [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", bloodGroup=" + bloodGroup + ", city=" + city + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", userType=" + userType + ", passwordHash=" + passwordHash + ", bloodStatusAddedOrNot="
				+ bloodStatusAddedOrNot + "]";
	}

	
	
}
