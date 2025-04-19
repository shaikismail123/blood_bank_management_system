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
@Table(name = "blood_donation")
public class BloodDonation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long donationId;

	@ManyToOne
	@JoinColumn(name = "donor_id", nullable = false)
	private MyUserDetails donor;

	private String patientId;

	private LocalDateTime donationDateTime;

	private Double glucoseLevel;

	@Column(length = 1000)
	private String notes;

	private String status; // PENDING, APPROVED, REJECTED

	public BloodDonation() {

	}

	public BloodDonation(Long donationId, MyUserDetails donor, String patientId, LocalDateTime donationDateTime,
			Double glucoseLevel, String notes, String status) {
		super();
		this.donationId = donationId;
		this.donor = donor;
		this.patientId = patientId;
		this.donationDateTime = donationDateTime;
		this.glucoseLevel = glucoseLevel;
		this.notes = notes;
		this.status = status;
	}

	public Long getDonationId() {
		return donationId;
	}

	public void setDonationId(Long donationId) {
		this.donationId = donationId;
	}

	public MyUserDetails getDonor() {
		return donor;
	}

	public void setDonor(MyUserDetails donor) {
		this.donor = donor;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
