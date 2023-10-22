package com.nisum.latam.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    @Test
    public void testPhoneRequest() {
        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber("1234567");
        phoneRequest.setCityCode("1");
        phoneRequest.setContryCode("57");

        assertEquals("1234567", phoneRequest.getNumber());
        assertEquals("1", phoneRequest.getCityCode());
        assertEquals("57", phoneRequest.getContryCode());
    }

    @Test
    public void testUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setEmail("john@example.com");
        userRequest.setPassword("password123");

        assertEquals("John Doe", userRequest.getName());
        assertEquals("john@example.com", userRequest.getEmail());
        assertEquals("password123", userRequest.getPassword());
    }

    @Test
    public void testUserRequestWithPhones() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setEmail("john@example.com");
        userRequest.setPassword("password123");

        List<PhoneRequest> phones = new ArrayList<>();
        PhoneRequest phone1 = new PhoneRequest();
        phone1.setNumber("1234567");
        phone1.setCityCode("1");
        phone1.setContryCode("57");
        phones.add(phone1);
        userRequest.setPhones(phones);

        assertEquals("John Doe", userRequest.getName());
        assertEquals("john@example.com", userRequest.getEmail());
        assertEquals("password123", userRequest.getPassword());
        assertEquals(1, userRequest.getPhones().size());
    }
}

