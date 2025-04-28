package com.capstone.demo.controller;

import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.AdminOperations;
import com.capstone.demo.service.AdminDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminOperationsControllerTest {
    
    @InjectMocks
    private AdminOperationsController adminOperationsController;
    
    @Mock
    private AdminDetailsServiceImpl adminDetailsServiceImpl;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminOperationsController).build();
    }
    
    @Test
    public void testSaveAdminDetails() throws Exception {
        // Prepare test data
        AdminOperations adminOperations = new AdminOperations();
        String response = "Details saved successfully";
        
        when(adminDetailsServiceImpl.saveAdminDetails(any(AdminOperations.class))).thenReturn(response);
        
        // Replace with actual JSON representation of AdminOperations
        mockMvc.perform(post("/admin/saveAdminApprovedDetails")
                        .contentType(APPLICATION_JSON)
                        .content("{\"field1\":\"value1\", \"field2\":\"value2\"}")) // Update this line
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }
    
    @Test
    public void testGetAllDonarsForAdmin() throws Exception {
        List<UserDetailsDto> donars = Arrays.asList(new UserDetailsDto(), new UserDetailsDto());
        
        when(adminDetailsServiceImpl.getAllDonarsForAdmin()).thenReturn(donars);
        
        mockMvc.perform(get("/admin/getAllDonars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    public void testGetAllRequestersForAdmin() throws Exception {
        List<UserDetailsDto> requesters = Arrays.asList(new UserDetailsDto(), new UserDetailsDto());
        
        when(adminDetailsServiceImpl.getAllRequestersForAdmin()).thenReturn(requesters);
        
        mockMvc.perform(get("/admin/getAllRequesters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    public void testGetAllRequesterForApproval() throws Exception {
        List<RequesterDetailsDto> requesters = Arrays.asList(new RequesterDetailsDto(), new RequesterDetailsDto());
        
        when(adminDetailsServiceImpl.getAllRequesterForApproval()).thenReturn(requesters);
        
        mockMvc.perform(get("/admin/getAllRequesterForApproval"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
