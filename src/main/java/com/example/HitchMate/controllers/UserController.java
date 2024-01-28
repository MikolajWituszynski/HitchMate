package com.example.HitchMate.controllers;

import com.example.HitchMate.Entity.User;
import com.example.HitchMate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path="/users")
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
