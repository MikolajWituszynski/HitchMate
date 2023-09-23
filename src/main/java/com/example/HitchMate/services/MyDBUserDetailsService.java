package com.example.HitchMate.services;

import com.example.HitchMate.Entity.User;
import com.example.HitchMate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;

public class MyDBUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // (1)
        // 1. Load the user from the users table by username. If not found, throw UsernameNotFoundException.

        User user = userRepository.findByUsername(username);
        if(user == null) {
             throw new UsernameNotFoundException("User not found with username: " + user.getUsername());
        }
        UserDetails uD = (UserDetails) user;
        return uD;
    }
}

