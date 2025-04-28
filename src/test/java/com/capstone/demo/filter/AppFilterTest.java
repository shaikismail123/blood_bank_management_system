package com.capstone.demo.filter;

import com.capstone.demo.service.JwtService;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;

public class AppFilterTest {
    
    @InjectMocks
    private AppFilter appFilter;
    
    @Mock
    private JwtService jwtService;
    
    @Mock
    private MyUserDetailsServiceImpl userDetailsServiceImpl;
    
    @Mock
    private HttpServletRequest request;
    
    @Mock
    private HttpServletResponse response;
    
    @Mock
    private FilterChain filterChain;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testDoFilterInternal_WithValidToken() throws Exception {
        // Arrange
        String token = "valid.token.here";
        String username = "testUser";
        
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.extractUsername(token)).thenReturn(username);
        
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsServiceImpl.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.validateToken(token, userDetails)).thenReturn(true);
        
        // Act
        appFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtService).extractUsername(token);
        verify(userDetailsServiceImpl).loadUserByUsername(username);
        verify(jwtService).validateToken(token, userDetails);
    }
    
    @Test
    public void testDoFilterInternal_WithInvalidToken() throws Exception {
        // Arrange
        String token = "invalid.token.here";
        String username = "testUser";
        
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.extractUsername(token)).thenReturn(username);
        
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsServiceImpl.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.validateToken(token, userDetails)).thenReturn(false);
        
        // Act
        appFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtService).extractUsername(token);
        verify(userDetailsServiceImpl).loadUserByUsername(username);
        verify(jwtService).validateToken(token, userDetails);
    }
}