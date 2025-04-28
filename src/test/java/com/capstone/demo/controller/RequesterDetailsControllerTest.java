package com.capstone.demo.controller;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.service.RequesterDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RequesterDetailsControllerTest {
    
    @InjectMocks
    private RequesterDetailsController requesterDetailsController;
    
    @Mock
    private RequesterDetailsServiceImpl requesterDetailsServiceImpl;
    
    @Mock
    private DefaultValues defaultValues; // Mocking DefaultValues
    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(requesterDetailsController).build();
    }
    
//    @Test
//    public void testSaveRequesterDetails() throws Exception {
//        RequesterDetailsDto requesterDetailsDto = new RequesterDetailsDto();
//        boolean saveResponse = true;
//        
//        when(requesterDetailsServiceImpl.saveRequesterDetails(any(RequesterDetailsDto.class))).thenReturn(saveResponse);
//        when(defaultValues.getMessage()).thenReturn(Map.of(AppConstants.SUCCESS, "Success"));
//        
//        mockMvc.perform(post("/requester/saveRequesterDetails")
//                        .contentType(APPLICATION_JSON)
//                        .content("{\"field1\":\"value1\", \"field2\":\"value2\"}")) // Replace with actual fields
//                .andExpect(status().isOk())
//                .andExpect(content().string("Success")); // Expecting the success message
//    }
    
    @Test
    public void testDeleteReqeusterById() throws Exception {
        Long requesterId = 1L;
        boolean deleteResponse = true;
        
        when(requesterDetailsServiceImpl.deleteRequestById(requesterId)).thenReturn(deleteResponse);
        when(defaultValues.getMessage()).thenReturn(Map.of(AppConstants.DELETE, "Delete"));
        
        mockMvc.perform(delete("/requester/deleteReqeusterById/{id}", requesterId))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete")); // Expecting the success message
    }
    
//    @Test
//    public void testGetReqeustDetailsById() throws Exception {
//        Long requesterId = 1L;
//        RequesterDetails requesterDetails = new RequesterDetails();
//        
//        when(requesterDetailsServiceImpl.getReqeustDetailsById(requesterId)).thenReturn(requesterDetails);
//        
//        mockMvc.perform(get("/requester/getReqeustDetailsById/{id}", requesterId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(APPLICATION_JSON));
//    }
    
   /* @Test
    public void testUpdateDonerDetails() throws Exception {
        UsersDto usersDto = new UsersDto();
        boolean updateResponse = true;
        
        when(requesterDetailsServiceImpl.updateRequesterDetails(any(UsersDto.class))).thenReturn(updateResponse);
        when(defaultValues.getMessage()).thenReturn(Map.of(AppConstants.UPDATE, "Update"));
        
        mockMvc.perform(put("/requester/updateRequester")
                        .contentType(APPLICATION_JSON)
                        .content("{\"field1\":\"value1\", \"field2\":\"value2\"}")) // Replace with actual fields
                .andExpect(status().isOk())
                .andExpect(content().string("Update")); // Expecting the success message
    }*/
}