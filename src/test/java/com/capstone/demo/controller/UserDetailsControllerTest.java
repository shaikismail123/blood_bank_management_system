/*
package com.capstone.demo.controller;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.service.DonarDetailsServiceImpl;
import com.capstone.demo.service.JwtService;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.capstone.demo.service.RequesterDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserDetailsControllerTest {
    
    @InjectMocks
    private UserDetailsController userDetailsController;
    
    @Mock
    private MyUserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private DonarDetailsServiceImpl donarDetailsServiceImpl;

    @Mock
    private RequesterDetailsServiceImpl requesterDetailsServiceImpl;



    @Mock
    private AuthenticationManager authManager;
    
    @Mock
    private JwtService jwt;
    
    @Mock
    private DefaultValues defaultValues; // Mocking DefaultValues

    @Autowired
    private ObjectMapper objectMapper; // Ensure Jackson dependency is in your build file

    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userDetailsController).build();
    }
    
    @Test
    public void testInsertUserDetails() throws Exception {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        boolean insertResponse = true;
        
        when(userDetailsServiceImpl.insertUserDetails(any(UserDetailsDto.class))).thenReturn(insertResponse);
        when(defaultValues.getMessage()).thenReturn(Map.of(AppConstants.SUCCESS, "Success"));
        
        mockMvc.perform(post("/userDetails/registerUser")
                        .contentType(APPLICATION_JSON)
                        .content("{\"field1\":\"value1\", \"field2\":\"value2\"}")) // Replace with actual fields
                .andExpect(status().isOk())
                .andExpect(content().string("Success")); // Expecting the success message
    }
    

// ...
    
    @Test
    public void testLoginCheck() throws Exception {
        // Create a MultiValueMap for the parameters
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("email", "test@example.com");
        params.add("password", "password");
        
        // Mock the authentication process
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("test@example.com", "password");
        Authentication authentication = mock(Authentication.class);
        when(authManager.authenticate(token)).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true); // Simulate successful authentication
        when(jwt.generateToken("test@example.com")).thenReturn("jwtToken");
        
        mockMvc.perform(post("/userDetails/login")
                        .params(params)) // Use the MultiValueMap here
                .andExpect(status().isOk())
                .andExpect(content().string("jwtToken")); // Expecting the JWT token
    }
    
    @Test
    public void testGetUserDetailsById() throws Exception {
        Long userId = 1L;
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        
        when(userDetailsServiceImpl.userDetailsById(userId)).thenReturn(userDetailsDto);
        
        mockMvc.perform(get("/userDetails/getUserDetailsById/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }
    @Test
    void updateDonerDetails_success_shouldReturnOk() throws Exception {
        // Arrange
        UsersDto usersDto = new UsersDto();
        when(donarDetailsServiceImpl.updateDonarDetails(any(UsersDto.class))).thenReturn(true);
        when(defaultValues.getMessage()).thenReturn(Collections.singletonMap(AppConstants.UPDATE, "Updated"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/updatedoner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Updated"));

        verify(donarDetailsServiceImpl, times(1)).updateDonarDetails(any());
    }

    @Test
    void updateRequester_success_shouldReturnOk() throws Exception {
        // Arrange
        UsersDto usersDto = new UsersDto();
        when(requesterDetailsServiceImpl.updateRequesterDetails(any(UsersDto.class))).thenReturn(true);
        when(defaultValues.getMessage()).thenReturn(Collections.singletonMap(AppConstants.UPDATE, "Updated"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/updateRequester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Updated"));

        verify(requesterDetailsServiceImpl, times(1)).updateRequesterDetails(any());
    }
}

*/
package com.capstone.demo.controller;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.service.DonarDetailsServiceImpl;
import com.capstone.demo.service.JwtService;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.capstone.demo.service.RequesterDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserDetailsControllerTest {

	@InjectMocks
	private UserDetailsController userDetailsController;

	@Mock
	private DonarDetailsServiceImpl donarDetailsServiceImpl;

	@Mock
	private RequesterDetailsService requesterDetailsServiceImpl;

	@Mock
	private AuthenticationManager authManager;

	@Mock
	private JwtService jwt;

	@Mock
	private MyUserDetailsServiceImpl userDetailsServiceImpl;

	@Mock
	private DefaultValues defaultValues;

	@Mock
	private Authentication authentication;

	private ObjectMapper mapper = new ObjectMapper();
	private Map<String, String> messages;
	private UserDetailsDto userDetailsDto;
	private UsersDto usersDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		messages = new HashMap<>();
		messages.put(AppConstants.SUCCESS, "Success Message");
		messages.put(AppConstants.UPDATE, "Update Message");
		messages.put(AppConstants.INVALID, "Invalid Credentials");

		userDetailsDto = new UserDetailsDto();
		userDetailsDto.setUserId(1L);
		userDetailsDto.setEmail("test@example.com");
		userDetailsDto.setPasswordHash("password");

		usersDto = new UsersDto();
		usersDto.setUserId(1L);
		usersDto.setFirstName("sathish");

	}

	@Test
	void insertUserDetails_success() throws Exception {
		when(userDetailsServiceImpl.insertUserDetails(any(UserDetailsDto.class))).thenReturn(true);
		when(defaultValues.getMessage()).thenReturn(messages);

		ResponseEntity<String> response = userDetailsController.insertUserDetails(userDetailsDto);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Success Message", response.getBody());
	}

	@Test
	void insertUserDetails_failure() throws Exception {
		when(userDetailsServiceImpl.insertUserDetails(any(UserDetailsDto.class))).thenReturn(false);

		ResponseEntity<String> response = userDetailsController.insertUserDetails(userDetailsDto);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(null, response.getBody());
	}

	@Test
	void loginCheck_success() {
		Map<String, String> loginParams = new HashMap<>();
		loginParams.put("email", "test@example.com");
		loginParams.put("password", "password");

		when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
		when(authentication.isAuthenticated()).thenReturn(true);
		when(jwt.generateToken(anyString())).thenReturn("mockedJwtToken");

		ResponseEntity<String> response = userDetailsController.loginCheck(loginParams);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("mockedJwtToken", response.getBody());
	}

	@Test
	void loginCheck_failure_authenticationException() {
		Map<String, String> loginParams = new HashMap<>();
		loginParams.put("email", "test@example.com");
		loginParams.put("password", "wrongpassword");

		when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
				.thenThrow(new RuntimeException("Authentication failed"));
		when(defaultValues.getMessage()).thenReturn(messages);

		ResponseEntity<String> response = userDetailsController.loginCheck(loginParams);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Invalid Credentials", response.getBody());
	}

	@Test
	void getUserDetailsById_success() throws UserNotFoundException {
		when(userDetailsServiceImpl.userDetailsById(anyLong())).thenReturn(userDetailsDto);

		ResponseEntity<UserDetailsDto> response = userDetailsController.getUserDetailsById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userDetailsDto, response.getBody());
	}

	@Test
	void getUserDetailsById_notFound() throws UserNotFoundException {
		when(userDetailsServiceImpl.userDetailsById(anyLong())).thenReturn(null);

		ResponseEntity<UserDetailsDto> response = userDetailsController.getUserDetailsById(1L);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(null, response.getBody());
	}

	@Test
	void updateDonerDetails_success() throws UserNotFoundException {
		when(donarDetailsServiceImpl.updateDonarDetails(any(UsersDto.class))).thenReturn(true);
		when(defaultValues.getMessage()).thenReturn(messages);

		ResponseEntity<String> response = userDetailsController.updateDonerDetails(usersDto);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Update Message", response.getBody());
	}

	@Test
	void updateDonerDetails_failure() throws UserNotFoundException {
		when(donarDetailsServiceImpl.updateDonarDetails(any(UsersDto.class))).thenReturn(false);

		ResponseEntity<String> response = userDetailsController.updateDonerDetails(usersDto);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(null, response.getBody());
	}

//	@Test
//	void updateRequester_success() throws UserNotFoundException {
//		when(requesterDetailsServiceImpl.updateRequesterDetails(any(UsersDto.class))).thenReturn(true);
//		when(defaultValues.getMessage()).thenReturn(messages);
//
//		ResponseEntity<String> response = userDetailsController.updateRequesterDetails(usersDto);
//
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals("Update Message", response.getBody());
//	}

	@Test
	void updateRequester_failure() throws UserNotFoundException {
		when(requesterDetailsServiceImpl.updateRequesterDetails(any(UsersDto.class))).thenReturn(false);

		ResponseEntity<String> response = userDetailsController.updateDonerDetails(usersDto);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(null, response.getBody());
	}
}
