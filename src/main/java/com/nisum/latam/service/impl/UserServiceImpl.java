package com.nisum.latam.service.impl;

import com.nisum.latam.components.UserParser;
import com.nisum.latam.entities.User;
import com.nisum.latam.model.UserRequest;
import com.nisum.latam.repository.UserRepository;
import com.nisum.latam.service.UserService;
import com.nisum.latam.util.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nisum.latam.util.ConstantUtil.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserParser userParser;

    public User registerUser(UserRequest userRequest) {
        validateUserRequest(userRequest);
        User newUser = userParser.createUserFromRequest(userRequest);
        return userRepository.save(newUser);
    }

    private void validateUserRequest(UserRequest userRequest) {
        if (!isValidEmail(userRequest.getEmail())) {
            throw new UserRegistrationException(FORMAT_MAIL_INVALID);
        }
        if (!isValidPassword(userRequest.getPassword())) {
            throw new UserRegistrationException(FORMAT_PASS_INVALID);
        }
        User existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser != null) {
            throw new UserRegistrationException(REGISTERED_MAIL);
        }
    }

    private boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    private boolean isValidPassword(String password) {
        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        return passwordMatcher.matches();
    }
}

