package com.capstone.demo.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "blood_availability")
@Data
public class BloodAvailability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bloodId;

	@NotBlank
	private String bloodGroup;
	private Integer quantity;
	private String city;

	public BloodAvailability() {

	}

	public BloodAvailability(Long bloodId, @NotBlank String bloodGroup, Integer quantity, String city) {
		super();
		this.bloodId = bloodId;
		this.bloodGroup = bloodGroup;
		this.quantity = quantity;
		this.city = city;
	}

	public Long getBloodId() {
		return bloodId;
	}

	public void setBloodId(Long bloodId) {
		this.bloodId = bloodId;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloodGroup, bloodId, city, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BloodAvailability other = (BloodAvailability) obj;
		return Objects.equals(bloodGroup, other.bloodGroup) && Objects.equals(bloodId, other.bloodId)
				&& Objects.equals(city, other.city) && Objects.equals(quantity, other.quantity);
	}
	
	
	

}