package com.example.HitchMate.security.config.auth;

import com.example.HitchMate.Entity.Role;
import com.example.HitchMate.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.HitchMate.Entity.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    public AuthenticationResponse register(RegisterRequest request) {
       User userEntity = new User();
               userEntity.setPassword(encoder.encode(request.getPassword()));
               userEntity.setEmail(request.getEmail());
               userEntity.setFirstname(request.getFirstName());
               userEntity.setLastname(request.getLastName());
        repository.save(userEntity);
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
