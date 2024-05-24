package org.example.tapville.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.example.tapville.controllers.rest.AuthEntity.AuthenticationRequest;
import org.example.tapville.controllers.rest.AuthEntity.AuthenticationResponse;
import org.example.tapville.controllers.rest.AuthEntity.RegisterRequest;
import org.example.tapville.exceptions.AuthorizationException;
import org.example.tapville.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody RegisterRequest request){
        try {
            AuthenticationResponse response = service.register(request);
            return ResponseEntity.ok(response);
        }catch (AuthorizationException e){
            System.out.println("Exception during registration: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>authenticate(@RequestBody AuthenticationRequest request){
        try{
            AuthenticationResponse response = service.authenticate(request);
            return ResponseEntity.ok(response);
        }catch (AuthorizationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
