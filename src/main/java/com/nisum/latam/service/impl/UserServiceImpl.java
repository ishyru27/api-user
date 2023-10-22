package com.nisum.latam.service.impl;

import com.nisum.latam.entities.Phone;
import com.nisum.latam.entities.User;
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

import static com.nisum.latam.util.Constant.EMAIL_REGEX;
import static com.nisum.latam.util.Constant.PASSWORD_REGEX;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserRequest userRequest) {
        // Validar el formato del correo y la clave
        if (!isValidEmail(userRequest.getEmail())) {
            throw new UserRegistrationException("El formato del correo es incorrecto");
        }

        if (!isValidPassword(userRequest.getPassword())) {
            throw new UserRegistrationException("El formato de la clave es incorrecto");
        }

        // Verificar si el correo ya existe
        User existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser != null) {
            throw new UserRegistrationException("El correo ya registrado");
        }

        // Crear un nuevo usuario
        User newUser = new User();
        UUID userId = UUID.randomUUID();
        Date currentDate = new Date();

        newUser.setId(userId);
        newUser.setName(userRequest.getName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(userRequest.getPassword());

        // Convertir PhoneRequest a Phone y asignar a la lista de phones
        List<Phone> phones = userRequest.getPhones().stream()
                .map(phoneRequest -> {
                    Phone phone = new Phone();
                    phone.setNumber(phoneRequest.getNumber());
                    phone.setCityCode(phoneRequest.getCityCode());
                    phone.setCountryCode(phoneRequest.getContryCode());
                    return phone;
                })
                .collect(Collectors.toList());
        newUser.setPhones(phones);

        // Configurar fechas y campos adicionales
        newUser.setCreated(currentDate);
        newUser.setModified(currentDate);
        newUser.setLastLogin(currentDate);

        String accessToken = UUID.randomUUID().toString();
        newUser.setToken(accessToken);
        newUser.setActive(true);

        // Guardar el nuevo usuario en la base de datos
        return userRepository.save(newUser);
    }
    private boolean isValidEmail(String email) {
        // Validar el formato del correo usando una expresión regular
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    private boolean isValidPassword(String password) {
        // Validar el formato de la clave usando una expresión regular
        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        return passwordMatcher.matches();
    }
}

