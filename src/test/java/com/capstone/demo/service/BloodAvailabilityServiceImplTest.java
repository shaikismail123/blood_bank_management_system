package com.capstone.demo.service;

import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.BloodAvailability;
import com.capstone.demo.exception.BloodNotAvailabilityException;
import com.capstone.demo.repository.BloodAvailabilityRepository;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BloodAvailabilityServiceImplTest {

	@Mock
	private BloodAvailabilityRepository bloodAvailabilityRepository;

	@Mock
	private MyUserDetailsRepository myUserDetailsRepository;

	@Mock
	private DefaultValues defaultValues;

	@InjectMocks
	private BloodAvailabilityServiceImpl bloodAvailabilityService;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		// Any additional setup can be done here
	}

	@Test
	public void testGetBloodDetails() throws BloodNotAvailabilityException {
		List<BloodAvailability> mockBloodDetails = Arrays.asList(new BloodAvailability());
		when(bloodAvailabilityRepository.findAll()).thenReturn(mockBloodDetails);

		List<BloodAvailability> result = bloodAvailabilityService.getBloodDetails();
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetBloodDetails_NoData() {
		when(bloodAvailabilityRepository.findAll()).thenReturn(new ArrayList<>());

		Exception exception = assertThrows(BloodNotAvailabilityException.class, () -> {
			bloodAvailabilityService.getBloodDetails();
		});
		assertEquals("Blood data not available ", exception.getMessage());
	}

//    @Test
//    public void testGetBloodDetailsBasedOnCityAndBloodGroup() throws BloodNotAvailabilityException {
//        String city = "New York";
//        String bloodGroup = "A+";
//        List<BloodAvailability> mockBloodDetails = Arrays.asList(new BloodAvailability());
//        when(bloodAvailabilityRepository.getBloodDetails(city, bloodGroup)).thenReturn(mockBloodDetails);
//        
//        List<BloodAvailability> result = bloodAvailabilityService.getBloodDetailsBasedOnCityAndBloodGroup(city, bloodGroup);
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }

//    @Test
//    public void testGetBloodDetailsBasedOnCityAndBloodGroup_NoData() {
//        String city = "New York";
//        String bloodGroup = "A+";
//        when(bloodAvailabilityRepository.getBloodDetails(city, bloodGroup)).thenReturn(new ArrayList<>());
//        
//        Exception exception = assertThrows(BloodNotAvailabilityException.class, () -> {
//            bloodAvailabilityService.getBloodDetailsBasedOnCityAndBloodGroup(city, bloodGroup);
//        });
//        assertEquals("Blood not available based on this cityNew York and blood group  A+", exception.getMessage());
//    }

//    @Test
//    public void testGetBloodDetailsBasedOnCity() throws BloodNotAvailabilityException {
//        String city = "New York";
//        List<BloodAvailability> mockBloodDetails = Arrays.asList(new BloodAvailability());
//        when(bloodAvailabilityRepository.getBloodDetailsBasedOnCity(city)).thenReturn(mockBloodDetails);
//        
//        List<BloodAvailability> result = bloodAvailabilityService.getBloodDetailsBasedOnCity(city);
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }

//    @Test
//    public void testGetBloodDetailsBasedOnCity_NoData() {
//        String city = "New York";
//        when(bloodAvailabilityRepository.getBloodDetailsBasedOnCity(city)).thenReturn(new ArrayList<>());
//        
//        Exception exception = assertThrows(BloodNotAvailabilityException.class, () -> {
//            bloodAvailabilityService.getBloodDetailsBasedOnCity(city);
//        });
//        assertEquals("Blood not available based on this city New York", exception.getMessage());
//    }

	@Test
	public void testGetBloodDetailsBasedOnBloodGroup() throws BloodNotAvailabilityException {
		String bloodGroup = "A+";
		List<BloodAvailability> mockBloodDetails = Arrays.asList(new BloodAvailability());
		when(bloodAvailabilityRepository.findByBloodGroup(bloodGroup)).thenReturn(mockBloodDetails);

		List<BloodAvailability> result = bloodAvailabilityService.getBloodDetailsBasedOnBloodGroup(bloodGroup);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

//    @Test
//    public void testGetBloodDetailsBasedOnBloodGroup_NoData() {
//        String bloodGroup = "A+";
//        when(bloodAvailabilityRepository.findByBloodGroup(bloodGroup)).thenReturn(new ArrayList<>());
//        
//        Exception exception = assertThrows(BloodNotAvailabilityException.class, () -> {
//            bloodAvailabilityService.getBloodDetailsBasedOnBloodGroup(bloodGroup);
//        });
//        assertEquals("Blood not available based on this blood group A+", exception.getMessage());
//    }

	@Test
	public void testAddingBloodCountFromDonars() {
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		userDetailsDto.setBloodGroup("A+");
		userDetailsDto.setCity("New York");

		BloodAvailability existingBlood = new BloodAvailability();
		existingBlood.setQuantity(String.valueOf(5));
		when(bloodAvailabilityRepository.findByBloodGroupAndCity("A+", "New York")).thenReturn(existingBlood);

		bloodAvailabilityService.addingBloodCountFromDonars(userDetailsDto);
		assertEquals(6, existingBlood.getQuantity());
		verify(bloodAvailabilityRepository).save(existingBlood);
	}

	@Test
	public void testAddingBloodCountFromDonars_NewBlood() {
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		userDetailsDto.setBloodGroup("B+");
		userDetailsDto.setCity("Los Angeles");

		when(bloodAvailabilityRepository.findByBloodGroupAndCity("B+", "Los Angeles")).thenReturn(null);

		bloodAvailabilityService.addingBloodCountFromDonars(userDetailsDto);
		verify(bloodAvailabilityRepository).save(any(BloodAvailability.class));
	}
}