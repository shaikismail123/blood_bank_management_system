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

//	@ManyToOne
//	@JoinColumn(name = "requster_id", nullable = false)
//	private MyUserDetails requesterId;	
//
//	@ManyToOne
//	@JoinColumn(name = "donor_id", nullable = false)
//	private MyUserDetails donarId;	

	private Long requsterId;

	private Long donarId;

	private String patientId; // requester Id and patient id are same

	private LocalDateTime donationDateTime;

	private Double glucoseLevel;

	@Column(length = 1000)
	private String notes;

	private String status; // PENDING, APPROVED, REJECTED

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getRequsterId() {
		return requsterId;
	}

	public void setRequsterId(Long requsterId) {
		this.requsterId = requsterId;
	}

	public Long getDonarId() {
		return donarId;
	}

	public void setDonarId(Long donarId) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DonarDetails(Long id, Long requsterId, Long donarId, String patientId, LocalDateTime donationDateTime,
			Double glucoseLevel, String notes, String status) {
		super();
		Id = id;
		this.requsterId = requsterId;
		this.donarId = donarId;
		this.patientId = patientId;
		this.donationDateTime = donationDateTime;
		this.glucoseLevel = glucoseLevel;
		this.notes = notes;
		this.status = status;
	}

	public DonarDetails() {
		super();
	}

	@Override
	public String toString() {
		return "DonarDetails [Id=" + Id + ", requsterId=" + requsterId + ", donarId=" + donarId + ", patientId="
				+ patientId + ", donationDateTime=" + donationDateTime + ", glucoseLevel=" + glucoseLevel + ", notes="
				+ notes + ", status=" + status + "]";
	}

}
