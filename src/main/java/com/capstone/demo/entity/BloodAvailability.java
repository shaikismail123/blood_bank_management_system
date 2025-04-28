package com.capstone.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blood_availability")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodAvailability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bloodId;

	@NotBlank
	private String bloodGroup;
	@NotBlank
	private String quantity;
	@NotBlank
	private String city;


}