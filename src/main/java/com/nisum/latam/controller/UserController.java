package com.nisum.latam.controller;

import com.nisum.latam.entities.User;
import com.nisum.latam.model.UserRequest;
import com.nisum.latam.service.UserService;
import com.nisum.latam.util.ErrorResponse;
import com.nisum.latam.util.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        try {
            User savedUser = userService.registerUser(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (UserRegistrationException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}


