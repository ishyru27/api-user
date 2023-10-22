package com.nisum.latam.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseTest {

    @Test
    public void testErrorResponse() {
        // Crear un objeto ErrorResponse
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Error message");

        // Verificar que el campo "message" se ha establecido correctamente
        assertEquals("Error message", errorResponse.getMessage());
    }
}
