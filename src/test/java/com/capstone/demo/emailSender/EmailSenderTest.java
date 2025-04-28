package com.capstone.demo.emailSender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;

public class EmailSenderTest {
    
    @Mock
    private JavaMailSender mailSender;
    
    @InjectMocks
    private EmailSender emailSender;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testSendEmail() {
        // Given
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";
        
        // When
        emailSender.sendEmail(to, subject, body);
        
        // Create the expected message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        
        // Then
        verify(mailSender).send(message); // Verify that mailSender's send method was called with the correct message
    }
}