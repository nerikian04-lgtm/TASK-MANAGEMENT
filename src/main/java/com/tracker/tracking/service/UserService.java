package com.tracker.tracking.service;

import com.tracker.tracking.model.User;
import com.tracker.tracking.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repo) {
        this.repo = repo;
        this.encoder = new BCryptPasswordEncoder();
    }

    // REGISTER
    public String register(User user) {

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return "Email is already registered";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);

        return "success";
    }

    // LOGIN
    public User login(String email, String password) {

        Optional<User> userOpt = repo.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (encoder.matches(password, user.getPassword())) {
                return user;
            }
        }

        return null;
    }
}