package com.example.HitchMate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashingExample {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = "abc123";

        String hashedPassword = passwordEncoder.encode(password);

        System.out.println("Hashed password: " + hashedPassword);
    }
}
