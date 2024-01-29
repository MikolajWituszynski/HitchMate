package com.example.HitchMate.controllers;

import com.example.HitchMate.Entity.User;
import com.example.HitchMate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/users")
@CrossOrigin(origins = "http://localhost:3001") // Allow requests from this origin
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
        System.out.println("Resgistering User....");
        User savedUser = userRepository.save(newUser);
        URI locationOfNewUser = ucb.path("/register").build().toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User foundUser = userRepository.findById(id).orElse(null);
        if (foundUser != null) {
            return ResponseEntity.ok(foundUser);
        } else {
            // Return a 404 Not Found response if the user with the given ID is not found
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username) {
        User foundUser = userRepository.findByUsername(username);
        if (foundUser != null) {
            return ResponseEntity.ok(foundUser);
        } else {
            // Return a 404 Not Found response if the user with the given ID is not found
            return ResponseEntity.notFound().build();
        }
    }

}
