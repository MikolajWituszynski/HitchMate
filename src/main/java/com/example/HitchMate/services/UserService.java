package com.example.HitchMate.services;

import com.example.HitchMate.dto.LoginRequest;
import com.example.HitchMate.dto.SignupRequest;
import com.example.HitchMate.entity.User;
import com.example.HitchMate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User addUser(User user) {
        userRepository.save(user);

        return user;
    }

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");

    }

    public List<User> getAllLocations() {
        return userRepository.findAll();
    }

    public User updateLocation(User user) {
        // Perform any necessary validation and transformations
        return userRepository.save(user);
    }
}
