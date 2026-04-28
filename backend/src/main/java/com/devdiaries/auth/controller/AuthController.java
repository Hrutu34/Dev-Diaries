package com.devdiaries.auth.controller;

import com.devdiaries.auth.dto.SignUpRequest;
import com.devdiaries.auth.dto.LoginRequest;
import com.devdiaries.auth.dto.AuthResponse;
import com.devdiaries.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Authentication endpoints
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "https://localhost:8080"})
public class AuthController {

    private final AuthService authService;

    /**
     * Signup endpoint
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        AuthResponse authResponse = authService.signup(signUpRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    /**
     * Login endpoint
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Backend is running");
    }

}
