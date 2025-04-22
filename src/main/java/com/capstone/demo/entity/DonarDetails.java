package com.capstone.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "donar_details")
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

	private String patientId; // requester Id and patient id are same

	private LocalDateTime donationDateTime;

	private Double glucoseLevel;

	@Column(length = 1000)
	private String notes;

	private String donarApprovedstatus; // PENDING, APPROVED, REJECTED this status for donor approved or not

	public DonarDetails() {

	}

	public DonarDetails(Long id, MyUserDetails requesterId, MyUserDetails donarId, String patientId,
			LocalDateTime donationDateTime, Double glucoseLevel, String notes, String donarApprovedstatus) {
		super();
		Id = id;
		this.requesterId = requesterId;
		this.donarId = donarId;
		this.patientId = patientId;
		this.donationDateTime = donationDateTime;
		this.glucoseLevel = glucoseLevel;
		this.notes = notes;
		this.donarApprovedstatus = donarApprovedstatus;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public MyUserDetails getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(MyUserDetails requesterId) {
		this.requesterId = requesterId;
	}

	public MyUserDetails getDonarId() {
		return donarId;
	}

	public void setDonarId(MyUserDetails donarId) {
		this.donarId = donarId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public LocalDateTime getDonationDateTime() {
		return donationDateTime;
	}

	public void setDonationDateTime(LocalDateTime donationDateTime) {
		this.donationDateTime = donationDateTime;
	}

	public Double getGlucoseLevel() {
		return glucoseLevel;
	}

	public void setGlucoseLevel(Double glucoseLevel) {
		this.glucoseLevel = glucoseLevel;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDonarApprovedstatus() {
		return donarApprovedstatus;
	}

	public void setDonarApprovedstatus(String donarApprovedstatus) {
		this.donarApprovedstatus = donarApprovedstatus;
	}

	@Override
	public String toString() {
		return "DonarDetails [Id=" + Id + ", requesterId=" + requesterId + ", donarId=" + donarId + ", patientId="
				+ patientId + ", donationDateTime=" + donationDateTime + ", glucoseLevel=" + glucoseLevel + ", notes="
				+ notes + ", donarApprovedstatus=" + donarApprovedstatus + "]";
	}

}
