package com.example.HitchMate.security.config.auth;

import com.example.HitchMate.Entity.Role;
import com.example.HitchMate.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getFirstName())
                .password(encoder.encode(request.getPassword()))
                .roles(String.valueOf(Role.USER))
                .build();
        repository.save(user);
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
