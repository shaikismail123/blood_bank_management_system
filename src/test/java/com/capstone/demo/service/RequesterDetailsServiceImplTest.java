package com.capstone.demo.service;

import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.emailSender.EmailSender;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.exception.RequesterNotFoundException;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.repository.AdminDetailsRepository;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.capstone.demo.repository.RequesterDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RequesterDetailsServiceImplTest {
    
    @InjectMocks
    private RequesterDetailsServiceImpl requesterDetailsService;
    
    @Mock
    private RequesterDetailsRepository requesterDetailsRepository;
    
    @Mock
    private MyUserDetailsRepository myUserDetailsRepository;
    
    @Mock
    private EmailSender emailSender;
    
    @Mock
    private AdminDetailsRepository adminDetailsRepository;
    @Mock
    private DefaultValues defaultValues;
    private ObjectMapper mapper = new ObjectMapper();
    private RequesterDetailsDto requesterDetailsDto;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        requesterDetailsDto = new RequesterDetailsDto();
        requesterDetailsDto.setDonarId(new MyUserDetails()); // Assuming this is set correctly
        requesterDetailsDto.setRequesterId(new MyUserDetails()); // Assuming this is set correctly
        requesterDetailsDto.setRequiredDate(String.valueOf(LocalDate.now()));
    }
    
    
    
    @Test
    public void testSaveRequesterDetails_Failure() {
        when(myUserDetailsRepository.findById(1L)).thenReturn(Optional.empty());
        
        boolean result = requesterDetailsService.saveRequesterDetails(requesterDetailsDto);
        assertFalse(result);
    }
    
    @Test
    public void testDeleteRequestById_Success() throws UserNotFoundException {
        when(requesterDetailsRepository.findById(1L)).thenReturn(Optional.of(new RequesterDetails()));
        
        boolean result = requesterDetailsService.deleteRequestById(1L);
        assertTrue(result);
        verify(requesterDetailsRepository).deleteById(1L);
    }
    
    
    
    @Test
    public void testGetReqeustDetailsById_Success() throws RequesterNotFoundException {
        RequesterDetails mockRequesterDetails = new RequesterDetails();
        when(requesterDetailsRepository.findById(1L)).thenReturn(Optional.of(mockRequesterDetails));
        
        RequesterDetails result = requesterDetailsService.getReqeustDetailsById(1L);
        assertNotNull(result);
        assertEquals(mockRequesterDetails, result);
    }
    
    
    @Test
    public void testGetAllRequestOfDonarForApproving_Success() throws RequesterNotFoundException {
        List<RequesterDetails> mockRequesterList = new ArrayList<>();
        mockRequesterList.add(new RequesterDetails());
        when(requesterDetailsRepository.getDonarDetails(1L)).thenReturn(mockRequesterList);
        
        List<RequesterDetailsDto> result = requesterDetailsService.getAllRequestOfDonarForApproving(1L);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
    
    @Test
    public void testGetAllRequestOfDonarForApproving_RequesterNotFound() {
        when(requesterDetailsRepository.getDonarDetails(1L)).thenReturn(new ArrayList<>());
        
        Exception exception = assertThrows(RequesterNotFoundException.class, () -> {
            requesterDetailsService.getAllRequestOfDonarForApproving(1L);
        });
        assertEquals("Requsters not found to send the donar based on this id 1", exception.getMessage());
    }
    
    @Test
    public void testUpdateRequesterDetails_Success() throws UserNotFoundException {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserId(1L);
        usersDto.setFirstName("John");
        usersDto.setLastName("Doe");
        usersDto.setCity("New York");
        usersDto.setBloodGroup("O+");
        usersDto.setPhoneNumber("1234567890");
        
        MyUserDetails mockUserDetails = new MyUserDetails();
        mockUserDetails.setUserId(1L);
        
        // Mock the behavior of finding the user by ID
        when(myUserDetailsRepository.findById(1L)).thenReturn(Optional.of(mockUserDetails));
        // Mock the behavior of saving the user details
        when(myUserDetailsRepository.save(mockUserDetails)).thenReturn(mockUserDetails);
        
        boolean result = requesterDetailsService.updateRequesterDetails(usersDto);
        assertTrue(result);
        assertEquals("John", mockUserDetails.getFirstName());
        assertEquals("Doe", mockUserDetails.getLastName());
        assertEquals("New York", mockUserDetails.getCity());
        assertEquals("O+", mockUserDetails.getBloodGroup());
        assertEquals("1234567890", mockUserDetails.getPhoneNumber());
    }
    
    @Test
    public void testUpdateRequesterDetails_UserNotFound() {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserId(1L);
        
        when(myUserDetailsRepository.findById(1L)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            requesterDetailsService.updateRequesterDetails(usersDto);
        });
        assertEquals("Id is not availble for updating the Donar please check the User Id 1", exception.getMessage());
    }
}