package com.devdiaries.auth.service;

import com.devdiaries.auth.dto.SignUpRequest;
import com.devdiaries.auth.dto.LoginRequest;
import com.devdiaries.auth.dto.AuthResponse;
import com.devdiaries.auth.model.User;
import com.devdiaries.auth.repository.UserRepository;
import com.devdiaries.auth.security.JwtTokenProvider;
import com.devdiaries.common.exception.ResourceAlreadyExistsException;
import com.devdiaries.common.exception.ResourceNotFoundException;
import com.devdiaries.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Authentication Service for user registration and login
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Register a new user
     */
    public AuthResponse signup(SignUpRequest signUpRequest) {
        log.info("Registering new user with email: {}", signUpRequest.getEmail());

        // Check if user already exists
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            log.warn("User with email {} already exists", signUpRequest.getEmail());
            throw new ResourceAlreadyExistsException("User with email " + signUpRequest.getEmail() + " already exists");
        }

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            log.warn("User with username {} already exists", signUpRequest.getUsername());
            throw new ResourceAlreadyExistsException("Username " + signUpRequest.getUsername() + " already exists");
        }

        // Create new user
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .emailVerified(false)
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);
        log.info("User successfully registered with id: {}", savedUser.getId());

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(savedUser.getEmail());

        return buildAuthResponse(token, savedUser);
    }

    /**
     * Login user
     */
    public AuthResponse login(LoginRequest loginRequest) {
        log.info("User login attempt with email: {}", loginRequest.getEmail());

        // Find user by email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                    log.warn("User not found with email: {}", loginRequest.getEmail());
                    return new ResourceNotFoundException("User not found with email: " + loginRequest.getEmail());
                });

        // Check if user is active
        if (!user.isActive()) {
            log.warn("User account is inactive: {}", loginRequest.getEmail());
            throw new UnauthorizedException("User account is inactive");
        }

        // Validate password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.warn("Invalid password for user: {}", loginRequest.getEmail());
            throw new UnauthorizedException("Invalid email or password");
        }

        log.info("User successfully logged in: {}", loginRequest.getEmail());

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(user.getEmail());

        return buildAuthResponse(token, user);
    }

    /**
     * Build AuthResponse from User and token
     */
    private AuthResponse buildAuthResponse(String token, User user) {
        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    /**
     * Get user by email
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

}
