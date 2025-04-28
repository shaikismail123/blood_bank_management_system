package com.capstone.demo.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppConstantsTest {
    
    @Test
    public void testSuccessConstant() {
        assertEquals("success", AppConstants.SUCCESS);
    }
    
    @Test
    public void testFailConstant() {
        assertEquals("fail", AppConstants.FAIL);
    }
    
    @Test
    public void testStrConstant() {
        assertEquals("", AppConstants.STR);
    }
    
    @Test
    public void testDeleteConstant() {
        assertEquals("delete", AppConstants.DELETE);
    }
    
    @Test
    public void testUpdateConstant() {
        assertEquals("update", AppConstants.UPDATE);
    }
    
    @Test
    public void testInvalidConstant() {
        assertEquals("invalid", AppConstants.INVALID);
    }
}
