package com.capstone.demo.controller;

import com.capstone.demo.entity.BloodAvailability;
import com.capstone.demo.service.BloodAvailabilityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BloodAvailabilityControllerTest {
    
    @InjectMocks
    private BloodAvailabilityController bloodAvailabilityController;
    
    @Mock
    private BloodAvailabilityServiceImpl bloodAvailabilityServiceImpl;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bloodAvailabilityController).build();
    }
    
    @Test
    public void testGetBloodDetails() throws Exception {
        List<BloodAvailability> bloodDetails = Arrays.asList(new BloodAvailability(), new BloodAvailability());
        
        when(bloodAvailabilityServiceImpl.getBloodDetails()).thenReturn(bloodDetails);
        
        mockMvc.perform(get("/blood/getBloodDetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    public void testGetBloodDetailsBasedOnCity() throws Exception {
        String city = "New York";
        List<BloodAvailability> bloodDetailsBasedOnCity = Arrays.asList(new BloodAvailability(), new BloodAvailability());
        
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCity(city)).thenReturn(bloodDetailsBasedOnCity);
        
        mockMvc.perform(get("/blood/getBloodDetailsBasedOnCity/{city}", city))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    public void testGetBloodDetailsBasedOnBloodGroup() throws Exception {
        String bloodGroup = "A+";
        List<BloodAvailability> bloodDetailsBasedOnGroup = Arrays.asList(new BloodAvailability(), new BloodAvailability());
        
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnBloodGroup(bloodGroup)).thenReturn(bloodDetailsBasedOnGroup);
        
        mockMvc.perform(get("/blood/getBloodDetailsBasedOnBloodGroup/{blood}", bloodGroup))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    public void testGetBloodDetailsBasedOnCityAndBloodGroup() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("city", "New York");
        params.put("bloodGroup", "A+");
        List<BloodAvailability> bloodDetails = Arrays.asList(new BloodAvailability(), new BloodAvailability());
        
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCityAndBloodGroup("New York", "A+")).thenReturn(bloodDetails);
        
        mockMvc.perform(get("/blood/getBloodDetailsBasedOnCityAndBloodGroup")
                        .param("city", "New York")
                        .param("blood", "A+"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}