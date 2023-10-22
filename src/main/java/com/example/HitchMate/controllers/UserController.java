package com.example.HitchMate.controllers;

import com.example.HitchMate.Entity.Marker;
import com.example.HitchMate.Entity.User;
import com.example.HitchMate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
       List<User> allUsers = userRepository.findAll();
        return ResponseEntity.ok(allUsers);
    }
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody User newUser, UriComponentsBuilder ucb) {
        // Your registration logic here, which may include saving the user to the database

        // Replace the following with your actual registration logic.
        User savedUser = userRepository.save(newUser);

        URI locationOfNewUser = ucb.path("/register").build().toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }
    @GetMapping("/username")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        User foundUser = userRepository.findByUsername(username);
        return ResponseEntity.ok(foundUser);
    }
}
