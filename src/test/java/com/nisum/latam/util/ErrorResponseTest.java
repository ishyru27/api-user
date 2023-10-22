package com.nisum.latam.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseTest {

    @Test
    public void testErrorResponse() {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Error message");
        assertEquals("Error message", errorResponse.getMessage());
    }
}
