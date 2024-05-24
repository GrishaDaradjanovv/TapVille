package org.example.tapville.services;

import lombok.RequiredArgsConstructor;
import org.example.tapville.configuration.JwtService;
import org.example.tapville.controllers.rest.AuthEntity.AuthenticationRequest;
import org.example.tapville.controllers.rest.AuthEntity.AuthenticationResponse;
import org.example.tapville.controllers.rest.AuthEntity.RegisterRequest;
import org.example.tapville.exceptions.AuthorizationException;
import org.example.tapville.models.Role;
import org.example.tapville.repositories.contracts.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        org.example.tapville.models.User user = new org.example.tapville.models.User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        if (jwtToken == null || jwtToken.isEmpty()) {
            throw new AuthorizationException("Registration failed!");
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername()
                        , request.getPassword())
        );
        org.example.tapville.models.User user = userRepository.getUserByUsername(request.getUsername())
                .orElseThrow(() -> new AuthorizationException("Invalid username or password!"));
        var jwtToken = jwtService.generateToken(user);
        if (jwtToken == null || jwtToken.isEmpty()) {
            throw new AuthorizationException("Authentication failed!");
        }
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

}
