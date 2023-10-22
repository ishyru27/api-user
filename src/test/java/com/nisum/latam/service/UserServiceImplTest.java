package com.nisum.latam.service;

import com.nisum.latam.entities.User;
import com.nisum.latam.model.PhoneRequest;
import com.nisum.latam.model.UserRequest;
import com.nisum.latam.repository.UserRepository;
import com.nisum.latam.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Juan Rodriguez");
        userRequest.setEmail("juan@rodriguez.org");
        userRequest.setPassword("Hunters2");

        List<PhoneRequest> phoneRequests = List.of(
                new PhoneRequest("1234567", "123", "1"),
                new PhoneRequest("9876543", "456", "7")
        );
        userRequest.setPhones(phoneRequests);


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

        Mockito.when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(savedUser);

        User registeredUser = userService.registerUser(userRequest);

        assertNotNull(registeredUser.getId());
        assertEquals(userRequest.getName(), registeredUser.getName());
        assertEquals(userRequest.getEmail(), registeredUser.getEmail());
        assertEquals(userRequest.getPassword(), registeredUser.getPassword());
        assertEquals(currentDate, registeredUser.getCreated());
        assertEquals(currentDate, registeredUser.getModified());
        assertEquals(currentDate, registeredUser.getLastLogin());
        assertNotNull(registeredUser.getToken());
        assertEquals(true, registeredUser.isActive());
    }
}
