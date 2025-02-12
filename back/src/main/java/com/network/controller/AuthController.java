package com.network.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.models.User;
import com.network.payload.request.LoginRequest;
import com.network.payload.request.SignupRequest;
import com.network.payload.response.MessageResponse;
import com.network.payload.response.UserResponse;
import com.network.repository.UserRepository;
import com.network.service.UserService;

import jakarta.validation.Valid;

/**
 * Controller for authentication
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    /**
     * Authenticates a user based on the provided login request.
     *
     * @param loginRequest the login request containing the user's email and password
     * @return a ResponseEntity containing the authenticated user's details
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        User user = this.userRepository.findByEmail(loginRequest.getEmail()).orElse(null);

        return ResponseEntity.ok(new UserResponse()
            .setId(user.getId())
            .setEmail(user.getEmail())
            .setUserName(user.getUserName()));
        }

    /**
     * Registers a new user based on the provided signup request.
     *
     * @param signUpRequest the signup request containing the user's email, username, and password
     * @return a ResponseEntity containing the newly created user's details or an error message if the email is already taken
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        User newUser = userService.createUser(
            signUpRequest.getEmail(), 
            signUpRequest.getUserName(), 
            passwordEncoder.encode(signUpRequest.getPassword())
            );

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
