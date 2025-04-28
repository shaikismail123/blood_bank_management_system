package com.capstone.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JwtServiceTest {
    
    @InjectMocks
    private JwtService jwtService;
    
    @Mock
    private UserDetails userDetails;
    
    private String email = "test@example.com";
    private String token;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        token = jwtService.generateToken(email);
    }
    
    @Test
    public void testGenerateToken() {
        // Generate the first token
        String firstToken = jwtService.generateToken(email);
        assertNotNull(firstToken);
        
        // Generate a second token with a different claim or email
        String secondEmail = "test2@example.com"; // Use a different email
        String secondToken = jwtService.generateToken(secondEmail);
        assertNotNull(secondToken);
        
        // Ensure the two tokens are not equal
        assertNotEquals(firstToken, secondToken); // Ensure different tokens are generated
    }
    
    @Test
    public void testExtractUsername() {
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals(email, extractedUsername);
    }
    
    @Test
    public void testExtractExpiration() {
        Date expiration = jwtService.extractExpiration(token);
        assertNotNull(expiration);
        assertTrue(expiration.after(new Date())); // Expiration should be in the future
    }
    

    
    @Test
    public void testValidateToken_Valid() {
        when(userDetails.getUsername()).thenReturn(email);
        assertTrue(jwtService.validateToken(token, userDetails));
    }
    
    @Test
    public void testValidateToken_Invalid() {
        when(userDetails.getUsername()).thenReturn("wrong@example.com");
        assertFalse(jwtService.validateToken(token, userDetails));
    }
}