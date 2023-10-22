package com.nisum.latam.service;

import com.nisum.latam.entities.User;
import com.nisum.latam.model.UserRequest;

public interface UserService {
    User registerUser(UserRequest userRequest);
}
