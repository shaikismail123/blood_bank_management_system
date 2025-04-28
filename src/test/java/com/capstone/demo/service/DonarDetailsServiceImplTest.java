package com.capstone.demo.service;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.entity.DonarDetails;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.repository.DonarDetailsRepository;
import com.capstone.demo.repository.MyUserDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DonarDetailsServiceImplTest {
    
    @Mock
    private MyUserDetailsRepository myUserDetailsRepository;
    
    @Mock
    private DonarDetailsRepository donarDetailsRepository;
    
    @Mock
    private DefaultValues defaultValues;
    
    @InjectMocks
    private DonarDetailsServiceImpl donarDetailsService;
    
    @BeforeEach
    public void setUp() {
        // Any additional setup can be done here
    }
    
    @Test
    public void testSaveDonarApprovedRequests_Success() {
        DonarDetails mockDonarDetails = new DonarDetails();
        mockDonarDetails.setId(1L);
        
        when(donarDetailsRepository.save(mockDonarDetails)).thenReturn(mockDonarDetails);
        when(defaultValues.getMessage()).thenReturn(Map.of(AppConstants.SUCCESS, "Success"));
        
        String result = donarDetailsService.saveDonarApprovedReqeusts(mockDonarDetails);
        assertEquals("Success", result);
    }
    
    @Test
    public void testUpdateDonarDetails_Success() throws UserNotFoundException {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserId(1L);
        usersDto.setFirstName("Jane");
        usersDto.setLastName("Doe");
        usersDto.setCity("New York");
        usersDto.setBloodGroup("A+");
        usersDto.setPhoneNumber("1234567890");
        
        MyUserDetails mockUserDetails = new MyUserDetails();
        mockUserDetails.setUserId(1L);
        
        when(myUserDetailsRepository.findById(1L)).thenReturn(Optional.of(mockUserDetails));
        when(myUserDetailsRepository.save(mockUserDetails)).thenReturn(mockUserDetails);
        
        boolean result = donarDetailsService.updateDonarDetails(usersDto);
        assertTrue(result);
        assertEquals("Jane", mockUserDetails.getFirstName());
    }
    
    @Test
    public void testUpdateDonarDetails_UserNotFound() {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserId(1L);
        
        when(myUserDetailsRepository.findById(1L)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            donarDetailsService.updateDonarDetails(usersDto);
        });
        assertEquals("Id is not availble for updating the Donar please check the User Id 1", exception.getMessage());
    }
}