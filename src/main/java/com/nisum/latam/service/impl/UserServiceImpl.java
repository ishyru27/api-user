package com.nisum.latam.service.impl;

import com.nisum.latam.entities.Phone;
import com.nisum.latam.entities.User;
import com.nisum.latam.model.PhoneRequest;
import com.nisum.latam.model.UserRequest;
import com.nisum.latam.repository.UserRepository;
import com.nisum.latam.service.UserService;
import com.nisum.latam.util.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.nisum.latam.util.ConstantUtil.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserRequest userRequest) {
        validateUserRequest(userRequest);
        User newUser = createUserFromRequest(userRequest);

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

    private User createUserFromRequest(UserRequest userRequest) {
        User newUser = new User();
        newUser.setId(UUID.randomUUID());
        newUser.setName(userRequest.getName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(userRequest.getPassword());

        List<Phone> phones = userRequest.getPhones().stream()
                .map(this::createPhoneFromRequest)
                .collect(Collectors.toList());
        newUser.setPhones(phones);

        Date currentDate = new Date();
        newUser.setCreated(currentDate);
        newUser.setModified(currentDate);
        newUser.setLastLogin(currentDate);

        String accessToken = UUID.randomUUID().toString();
        newUser.setToken(accessToken);
        newUser.setActive(true);

        return newUser;
    }
    private Phone createPhoneFromRequest(PhoneRequest phoneRequest) {
        Phone phone = new Phone();
        phone.setNumber(phoneRequest.getNumber());
        phone.setCityCode(phoneRequest.getCityCode() != null ? phoneRequest.getCityCode() : "");
        phone.setCountryCode(phoneRequest.getContryCode() != null ? phoneRequest.getContryCode() : "");
        return phone;
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

