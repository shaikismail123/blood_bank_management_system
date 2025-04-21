package com.capstone.demo.dto;

import com.capstone.demo.entity.MyUserDetails;

import jakarta.persistence.Column;

public class RequesterDetailsDto {

	private Long id;

	private MyUserDetails requesterId;

	private MyUserDetails donarId;

//	private Long requesterId;
//
//	private Long donarId;

	private String patientName;

	private String requiredBloodGroup;

	private String city;

	private String doctorName;

	private String hospitalName;

	private String hospitalAddress;

	private String requiredDate;

	private String contactName;

	private String contactNumber;

	private String contactEmail;

	@Column(length = 1000)
	private String message;

	private String status; // PENDING, APPROVED, REJECTED

	public RequesterDetailsDto() {

	}

	public RequesterDetailsDto(Long id, MyUserDetails requesterId, MyUserDetails donarId, String patientName,
			String requiredBloodGroup, String city, String doctorName, String hospitalName, String hospitalAddress,
			String requiredDate, String contactName, String contactNumber, String contactEmail, String message,
			String status) {
		super();
		this.id = id;
		this.requesterId = requesterId;
		this.donarId = donarId;
		this.patientName = patientName;
		this.requiredBloodGroup = requiredBloodGroup;
		this.city = city;
		this.doctorName = doctorName;
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		this.requiredDate = requiredDate;
		this.contactName = contactName;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
		this.message = message;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getRequiredBloodGroup() {
		return requiredBloodGroup;
	}

	public void setRequiredBloodGroup(String requiredBloodGroup) {
		this.requiredBloodGroup = requiredBloodGroup;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RequesterDetailsDto [id=" + id + ", requesterId=" + requesterId + ", donarId=" + donarId
				+ ", patientName=" + patientName + ", requiredBloodGroup=" + requiredBloodGroup + ", city=" + city
				+ ", doctorName=" + doctorName + ", hospitalName=" + hospitalName + ", hospitalAddress="
				+ hospitalAddress + ", requiredDate=" + requiredDate + ", contactName=" + contactName
				+ ", contactNumber=" + contactNumber + ", contactEmail=" + contactEmail + ", message=" + message
				+ ", status=" + status + "]";
	}

	
}
