package com.nisum.latam.controller;

import com.nisum.latam.entities.User;
import com.nisum.latam.model.UserRequest;
import com.nisum.latam.service.UserService;
import com.nisum.latam.util.UserRegistrationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUserSuccess() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Juan Rodriguez");
        userRequest.setEmail("juan@rodriguez.org");
        userRequest.setPassword("hunter2");

        User savedUser = new User();
        savedUser.setId(UUID.randomUUID());
        savedUser.setName(userRequest.getName());
        savedUser.setEmail(userRequest.getEmail());
        savedUser.setPassword(userRequest.getPassword());
        Date currentDate = new Date();
        savedUser.setCreated(currentDate);
        savedUser.setModified(currentDate);
        savedUser.setLastLogin(currentDate);
        savedUser.setToken(UUID.randomUUID().toString());
        savedUser.setActive(true);

        Mockito.when(userService.registerUser(userRequest)).thenReturn(savedUser);

        ResponseEntity<?> responseEntity = userController.registerUser(userRequest);
        assertEquals(savedUser, responseEntity.getBody());
    }

    @Test
    public void testRegisterUserDuplicateEmail() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Juan Rodriguez");
        userRequest.setEmail("juan@rodriguez.org");
        userRequest.setPassword("hunter2");

        Mockito.when(userService.registerUser(userRequest))
                .thenThrow(new UserRegistrationException("El correo ya registrado"));

        ResponseEntity<?> responseEntity = userController.registerUser(userRequest);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
