package com.cars.myApps.services;

import com.cars.myApps.dtos.UserRegistration;
import com.cars.myApps.model.Role;
import com.cars.myApps.model.User;
import com.cars.myApps.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UsersRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(
            UsersRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkIfMailIsTaken(String email) {
        return userRepository.checkIfMailExists(email);
    }

    public boolean checkIfNameIsTaken(String email) {
        return userRepository.checkIfNameExists(email);
    }

    public void createUser(UserRegistration registration) {
        var user = new User();
        user.setName(registration.getName());
        user.setEmail(registration.getEmail());
        user.setRole(Role.USER);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        userRepository.save(user);
    }
}

