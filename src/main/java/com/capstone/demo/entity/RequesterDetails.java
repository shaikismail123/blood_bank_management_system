package com.capstone.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "requester_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequesterDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "requester_id", nullable = false)
	private MyUserDetails requesterId;

	@ManyToOne
	@JoinColumn(name = "donar_id", nullable = false)
	private MyUserDetails donarId;
	
	@NotBlank
	@Column
	private String patientName;
	
	@NotBlank
	@Column
	private String requiredBloodGroup;

	@NotBlank
	@Column
	private String city;

	@NotBlank
	@Column
	private String doctorName;

	@Column
	private String hospitalName;

	@Column
	private String hospitalAddress;

	@Column
	private String requiredDate;

	@Column
	private String contactName;

	@Column
	private String contactNumber;
	
	@Email
	@Column
	private String contactEmail;

	@Column(length = 1000)
	private String message;

	@NotBlank
	private String status; // PENDING, APPROVED, REJECTED  this is for admin approved or not 



}
