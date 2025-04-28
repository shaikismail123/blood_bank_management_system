package com.capstone.demo.controller;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.entity.DonarDetails;
import com.capstone.demo.service.DonarDetailsServiceImpl;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.capstone.demo.service.RequesterDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DonarDetailsControllerTest {

    @InjectMocks
    private DonarDetailsController donarDetailsController;

    @Mock
    private DonarDetailsServiceImpl donarDetailsServiceImpl;

    @Mock
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    @Mock
    private RequesterDetailsServiceImpl requesterDetailsServiceImpl;

    private MockMvc mockMvc;
    @Mock
    private DefaultValues defaultValues; // Mocking DefaultValues
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donarDetailsController).build();
    }
    
    @Test
    public void testSaveDonarApprovedReqeusts() throws Exception {
        DonarDetails donarDetails = new DonarDetails();
        String responseMessage = "Success";
        
        when(donarDetailsServiceImpl.saveDonarApprovedReqeusts(any(DonarDetails.class))).thenReturn(responseMessage);
        when(defaultValues.getMessage()).thenReturn(Map.of(AppConstants.SUCCESS, "Success")); // Mocking the message response
        
        mockMvc.perform(post("/donar/saveDonarApprovedReqeusts")
                        .contentType(APPLICATION_JSON)
                        .content("{\"field1\":\"value1\", \"field2\":\"value2\"}")) // Replace with actual fields
                .andExpect(status().isOk())
                .andExpect(content().string("Success")); // Expecting the success message
    }
    
    

    @Test
    public void testDeleteDonerDetails() throws Exception {
        Long donarId = 1L;
        boolean deleteResponse = true;
        
        // Mocking the delete method to return true
        when(myUserDetailsServiceImpl.deleteDonerDetails(donarId)).thenReturn(deleteResponse);
        // Mocking the message response for deletion
        when(defaultValues.getMessage()).thenReturn(Map.of(AppConstants.DELETE, "Delete"));
        
        mockMvc.perform(delete("/donar/deletedoner/{id}", donarId))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete")); // Expecting the success message
    }

    @Test
    public void testGetAllRequestOfDonarForApproving() throws Exception {
        Long donarId = 1L;
        List<RequesterDetailsDto> requesters = Arrays.asList(new RequesterDetailsDto(), new RequesterDetailsDto());

        when(requesterDetailsServiceImpl.getAllRequestOfDonarForApproving(donarId)).thenReturn(requesters);

        mockMvc.perform(get("/donar/getAllRequestOfDonar/{donarId}", donarId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}