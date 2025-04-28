package com.capstone.demo.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
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
@Table(name = "admin_operations")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdminOperations {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminId;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "requester_id", nullable = false, referencedColumnName = "id")
	private RequesterDetails requesterDetails;

	@NotBlank
	private String approvalStatus;

	@UpdateTimestamp
	@Column(name = "updated_date", insertable = false)
	private LocalDate updatedDate;

	@CreationTimestamp
	@Column(name = "insertable_date", updatable = false)
	private LocalDate insertableDate;

}
