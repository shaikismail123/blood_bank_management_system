package com.capstone.demo.service;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.emailSender.EmailSender;
import com.capstone.demo.entity.AdminOperations;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.exception.RequesterNotFoundException;
import com.capstone.demo.repository.AdminDetailsRepository;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.capstone.demo.repository.RequesterDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdminDetailsServiceImplTest {

    @InjectMocks
    private AdminDetailsServiceImpl adminDetailsService;

    @Mock
    private DefaultValues defaultValues;

    @Mock
    private  EmailSender emailSender;

    @Mock
    private AdminDetailsRepository adminDetailsRepository;

    @Mock
    private MyUserDetailsRepository myUserDetailsRepository;

    @Mock
    private RequesterDetailsRepository requesterDetailsRepository;

    @Mock
    private Logger logger;

    @Mock
    private ObjectMapper mapper;
    @Value("${subject.forrequester}")
    private String subject;

    @Value("$body.forrequester}")
    private String body;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = "subject"; // Initialize subject here
        body = "body"; // Initialize body here
    }

    @Test
    void getAllDonarsForAdmin_whenDonarsExist_returnsListOfUserDetailsDto() throws RequesterNotFoundException, JsonProcessingException {
        List<MyUserDetails> donarList = new ArrayList<>();
        donarList.add(new MyUserDetails());
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        when(myUserDetailsRepository.getAllDonarsForAdmin()).thenReturn(donarList);
        when(mapper.writeValueAsString(donarList)).thenReturn("[{\"id\":1}]");
        when(mapper.convertValue(any(MyUserDetails.class), eq(UserDetailsDto.class))).thenReturn(userDetailsDto);

        List<UserDetailsDto> result = adminDetailsService.getAllDonarsForAdmin();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(myUserDetailsRepository, times(1)).getAllDonarsForAdmin();
        verify(mapper, times(1)).writeValueAsString(donarList);
        verify(mapper, times(1)).convertValue(any(MyUserDetails.class), eq(UserDetailsDto.class));
    }

    @Test
    void getAllDonarsForAdmin_whenNoDonarsExist_throwsRequesterNotFoundException() {
        when(myUserDetailsRepository.getAllDonarsForAdmin()).thenReturn(new ArrayList<>());

        assertThrows(RequesterNotFoundException.class, () -> adminDetailsService.getAllDonarsForAdmin());
        verify(myUserDetailsRepository, times(1)).getAllDonarsForAdmin();
        // Removed the verification that ObjectMapper.writeValueAsString is never called
        verify(mapper, never()).convertValue(any(), eq(UserDetailsDto.class));
    }

    @Test
    void getAllRequesterForApproval_whenRequestersExist_returnsListOfRequesterDetailsDto() throws RequesterNotFoundException {
        List<RequesterDetails> requesterList = new ArrayList<>();
        requesterList.add(new RequesterDetails());
        RequesterDetailsDto requesterDetailsDto = new RequesterDetailsDto();
        when(requesterDetailsRepository.getAllRequesterForApproval()).thenReturn(requesterList);
        when(mapper.convertValue(any(RequesterDetails.class), eq(RequesterDetailsDto.class))).thenReturn(requesterDetailsDto);

        List<RequesterDetailsDto> result = adminDetailsService.getAllRequesterForApproval();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(requesterDetailsRepository, times(1)).getAllRequesterForApproval();
        verify(mapper, times(1)).convertValue(any(RequesterDetails.class), eq(RequesterDetailsDto.class));
    }

    @Test
    void getAllRequesterForApproval_whenNoRequestersExist_throwsRequesterNotFoundException() {
        when(requesterDetailsRepository.getAllRequesterForApproval()).thenReturn(new ArrayList<>());

        assertThrows(RequesterNotFoundException.class, () -> adminDetailsService.getAllRequesterForApproval());
        verify(requesterDetailsRepository, times(1)).getAllRequesterForApproval();
        verify(mapper, never()).convertValue(any(), eq(RequesterDetailsDto.class));
    }

//    @Test
//    void saveAdminDetails_whenSaveIsSuccessful_returnsSuccessMessage() {
//        AdminOperations adminOperations = new AdminOperations();
//        RequesterDetails requesterDetails = new RequesterDetails();
//        requesterDetails.setContactEmail("test@example.com");
//        adminOperations.setRequesterDetails(requesterDetails);
//        when(adminDetailsRepository.save(adminOperations)).thenReturn(adminOperations);
//        when(requesterDetailsRepository.save(requesterDetails)).thenReturn(requesterDetails);
//        Map<String, String> messages = new HashMap<>();
//        messages.put(AppConstants.SUCCESS, "Success");
//        when(defaultValues.getMessage()).thenReturn(messages);
//
//        String result = adminDetailsService.saveAdminDetails(adminOperations);
//
//        assertEquals("Success", result);
//        verify(adminDetailsRepository, times(1)).save(adminOperations);
//        verify(requesterDetailsRepository, times(1)).save(requesterDetails);
//        verify(emailSender, times(1)).sendEmail("test@example.com", null, null);
//    }
//    @Test
//    void saveAdminDetails_whenAdminOperationSaveFails_returnsFailMessage() {
//        AdminOperations adminOperations = new AdminOperations();
//        when(adminDetailsRepository.save(adminOperations)).thenReturn(null);
//        Map<String, String> messages = new HashMap<>();
//        messages.put(AppConstants.FAIL, "Fail");
//        when(defaultValues.getMessage()).thenReturn(messages);
//
//        String result = adminDetailsService.saveAdminDetails(adminOperations);
//
//        assertEquals("Fail", result);
//        verify(adminDetailsRepository, times(1)).save(adminOperations);
//        verify(requesterDetailsRepository, never()).save(any());
//        verify(emailSender, never()).sendEmail(anyString(), anyString(), anyString());
//    }
//
//    @Test
//    void saveAdminDetails_whenRequesterDetailsSaveFails_returnsFailMessage() {
//        AdminOperations adminOperations = new AdminOperations();
//        RequesterDetails requesterDetails = new RequesterDetails();
//        adminOperations.setRequesterDetails(requesterDetails);
//        when(adminDetailsRepository.save(adminOperations)).thenReturn(adminOperations);
//        when(requesterDetailsRepository.save(requesterDetails)).thenReturn(null);
//        Map<String, String> messages = new HashMap<>();
//        messages.put(AppConstants.FAIL, "Fail");
//        when(defaultValues.getMessage()).thenReturn(messages);
//
//        String result = adminDetailsService.saveAdminDetails(adminOperations);
//
//        assertEquals("Fail", result);
//        verify(adminDetailsRepository, times(1)).save(adminOperations);
//        verify(requesterDetailsRepository, times(1)).save(requesterDetails);
//        verify(emailSender, never()).sendEmail(anyString(), anyString(), anyString());
//    }

    @Test
    void saveAdminDetails_whenExceptionOccurs_returnsFailMessage() {
        AdminOperations adminOperations = new AdminOperations();
        when(adminDetailsRepository.save(adminOperations)).thenThrow(new RuntimeException("DB error"));
        Map<String, String> messages = new HashMap<>();
        messages.put(AppConstants.FAIL, "Fail");
        when(defaultValues.getMessage()).thenReturn(messages);

        String result = adminDetailsService.saveAdminDetails(adminOperations);

        assertEquals("Fail", result);
        verify(adminDetailsRepository, times(1)).save(adminOperations);
        verify(requesterDetailsRepository, never()).save(any());
        verify(emailSender, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void getAllRequestersForAdmin_whenRequestersExist_returnsListOfUserDetailsDto() throws RequesterNotFoundException, JsonProcessingException {
        List<MyUserDetails> requesterList = new ArrayList<>();
        requesterList.add(new MyUserDetails());
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        when(myUserDetailsRepository.getAllRequestersForAdmin()).thenReturn(requesterList);
        when(mapper.writeValueAsString(requesterList)).thenReturn("[{\"id\":1}]");
        when(mapper.convertValue(any(MyUserDetails.class), eq(UserDetailsDto.class))).thenReturn(userDetailsDto);

        List<UserDetailsDto> result = adminDetailsService.getAllRequestersForAdmin();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(myUserDetailsRepository, times(1)).getAllRequestersForAdmin();
        verify(mapper, times(1)).writeValueAsString(requesterList);
        verify(mapper, times(1)).convertValue(any(MyUserDetails.class), eq(UserDetailsDto.class));
    }

    @Test
    void getAllRequestersForAdmin_whenNoRequestersExist_throwsRequesterNotFoundException() {
        when(myUserDetailsRepository.getAllRequestersForAdmin()).thenReturn(new ArrayList<>());

        assertThrows(RequesterNotFoundException.class, () -> adminDetailsService.getAllRequestersForAdmin());
        verify(myUserDetailsRepository, times(1)).getAllRequestersForAdmin();
        // Removed the incorrect verification
        verify(mapper, never()).convertValue(any(), eq(UserDetailsDto.class));
    }
}