package com.example.HitchMate.security.config.auth;

import com.example.HitchMate.repositories.UserRepository;
import com.example.HitchMate.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.HitchMate.Entity.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
       User userEntity = new User();
                userEntity.setUsername(request.getUsername());
               userEntity.setPassword(encoder.encode(request.getPassword()));
               userEntity.setEmail(request.getEmail());
               userEntity.setFirstname(request.getFirstName());
               userEntity.setLastname(request.getLastName());
        repository.save(userEntity);
        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUserName());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
