package com.nisum.latam.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    @Test
    public void testPhoneRequest() {
        // Crear un objeto PhoneRequest
        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber("1234567");
        phoneRequest.setCitycode("1");
        phoneRequest.setContrycode("57");

        // Verificar que los campos se han establecido correctamente
        assertEquals("1234567", phoneRequest.getNumber());
        assertEquals("1", phoneRequest.getCitycode());
        assertEquals("57", phoneRequest.getContrycode());
    }

    @Test
    public void testUserRequest() {
        // Crear un objeto UserRequest
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setEmail("john@example.com");
        userRequest.setPassword("password123");

        // Verificar que los campos se han establecido correctamente
        assertEquals("John Doe", userRequest.getName());
        assertEquals("john@example.com", userRequest.getEmail());
        assertEquals("password123", userRequest.getPassword());
    }

    @Test
    public void testUserRequestWithPhones() {
        // Crear un objeto UserRequest con una lista de PhoneRequest
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setEmail("john@example.com");
        userRequest.setPassword("password123");

        // Crear una lista de PhoneRequest
        List<PhoneRequest> phones = new ArrayList<>();
        PhoneRequest phone1 = new PhoneRequest();
        phone1.setNumber("1234567");
        phone1.setCitycode("1");
        phone1.setContrycode("57");
        phones.add(phone1);
        userRequest.setPhones(phones);

        // Verificar que los campos se han establecido correctamente
        assertEquals("John Doe", userRequest.getName());
        assertEquals("john@example.com", userRequest.getEmail());
        assertEquals("password123", userRequest.getPassword());
        assertEquals(1, userRequest.getPhones().size());
    }
}

