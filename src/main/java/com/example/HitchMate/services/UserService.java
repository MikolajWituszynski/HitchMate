package com.example.HitchMate.services;

import com.example.HitchMate.entity.Location;
import com.example.HitchMate.entity.User;
import com.example.HitchMate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
       userRepository.deleteById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);

    }

    public List<User> getAllLocations() {
        return userRepository.findAll();
    }

    public User updateLocation(User user) {
        // Perform any necessary validation and transformations
        return userRepository.save(user);
    }
}
