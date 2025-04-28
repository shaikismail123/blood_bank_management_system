package com.capstone.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "donar_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonarDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@ManyToOne
	@JoinColumn(name = "requester_id", nullable = false)
	private MyUserDetails requesterId;

	@ManyToOne
	@JoinColumn(name = "donar_id", nullable = false)
	private MyUserDetails donarId;

	@Column
	private String patientId; // requester Id and patient id are same

	@Column
	private String donationDateTime;

	@Column
	private String glucoseLevel;

	@Column(length = 1000)
	private String notes;

	@Column
	@NotBlank
	private String donarApprovedstatus; // PENDING, APPROVED, REJECTED this status for donor approved or not

}
