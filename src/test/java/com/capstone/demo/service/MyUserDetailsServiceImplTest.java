package com.capstone.demo.service;

import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyUserDetailsServiceImplTest {
    
    @InjectMocks
    private MyUserDetailsServiceImpl userDetailsService;
    
    @Mock
    private MyUserDetailsRepository userDetailsRepository;
    
    @Mock
    private BloodAvailabilityServiceImpl bloodAvailabilityServiceImpl;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    private ObjectMapper mapper = new ObjectMapper();
    
    private UserDetailsDto userDetailsDto;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetailsDto = new UserDetailsDto();
        userDetailsDto.setEmail("test@example.com");
        userDetailsDto.setPasswordHash("password");
        userDetailsDto.setUserType("DONAR");
    }
    
    
    @Test
    public void testInsertUserDetails_Failure() {
        when(passwordEncoder.encode(userDetailsDto.getPasswordHash())).thenReturn("encodedPassword");
        when(userDetailsRepository.save(any(MyUserDetails.class))).thenThrow(new RuntimeException("Database error"));
        
        boolean result = userDetailsService.insertUserDetails(userDetailsDto);
        assertFalse(result);
    }
    
    @Test
    public void testUserDetailsById_Success() throws UserNotFoundException {
        MyUserDetails mockUserDetails = new MyUserDetails();
        mockUserDetails.setEmail("test@example.com");
        when(userDetailsRepository.findById(1L)).thenReturn(Optional.of(mockUserDetails));
        
        UserDetailsDto result = userDetailsService.userDetailsById(1L);
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }
    
    @Test
    public void testUserDetailsById_UserNotFound() {
        when(userDetailsRepository.findById(1L)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userDetailsService.userDetailsById(1L);
        });
        assertEquals("User Not Found With this ID 1", exception.getMessage());
    }
    
    @Test
    public void testDeleteDonerDetails_Success() throws UserNotFoundException {
        MyUserDetails mockUserDetails = new MyUserDetails();
        when(userDetailsRepository.findById(1L)).thenReturn(Optional.of(mockUserDetails));
        
        boolean result = userDetailsService.deleteDonerDetails(1L);
        assertTrue(result);
        verify(userDetailsRepository).deleteById(1L);
    }
    
    @Test
    public void testDeleteDonerDetails_UserNotFound() {
        when(userDetailsRepository.findById(1L)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userDetailsService.deleteDonerDetails(1L);
        });
        assertEquals("This Donar id 1 is not available please check once...! ", exception.getMessage());
    }
    
    @Test
    public void testLoadUserByUsername_Success() {
        MyUserDetails mockUserDetails = new MyUserDetails();
        mockUserDetails.setEmail("test@example.com");
        mockUserDetails.setPasswordHash("encodedPassword");
        when(userDetailsRepository.findByEmail("test@example.com")).thenReturn(mockUserDetails);
        
        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");
        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
    }
    
//    @Test
//    public void testLoadUserByUsername_UserNotFound() {
//        when(userDetailsRepository.findByEmail("wrong@example.com")).thenReturn(null);
//        
//        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
//            userDetailsService.loadUserByUsername("wrong@example.com");
//        });
//        assertEquals("User not found", exception.getMessage());
//    }
}