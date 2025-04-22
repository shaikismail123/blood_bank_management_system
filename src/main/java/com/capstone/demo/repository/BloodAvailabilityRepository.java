package com.capstone.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.demo.entity.BloodAvailability;

public interface BloodAvailabilityRepository extends JpaRepository<BloodAvailability, Long> {

	@Query(value = "select * from blood_availability where city=?1", nativeQuery = true)
	List<BloodAvailability> getBloodDetailsBasedOnCity(String city);

	@Query(value = "select sum(quantity) from blood_availability where blood_group= ?1", nativeQuery = true)
	List<BloodAvailability> getBloodDetailsBasedOnBloodGroup(String bloodGroup);

	@Query(value = "select * from blood_availability where city= ?1 and blood_group= ?2", nativeQuery = true)
	List<BloodAvailability> getBloodDetails(String city, String bloodGroup);

	@Query(value = "select distinct blood_group from blood_availability", nativeQuery = true)
	List<String> getDistinctBloodGroups();

	List<BloodAvailability> findByBloodGroup(String bloodGroup);

	BloodAvailability findByBloodGroupAndCity(String bloodGroup, String city);

}
