package com.network.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.dto.UserDto;
import com.network.exceptions.BadRequestException;
import com.network.exceptions.UnauthorizedException;
import com.network.payload.request.LoginRequest;
import com.network.payload.request.SignupRequest;
import com.network.payload.response.AuthReponse;
import com.network.services.AuthService;
import com.network.services.UserService;

/**
 * Controller for authentication
 * 
 * This controller provides endpoints for user registration, login, and retrieving
 * the authenticated user's information.
 */
@RequestMapping("auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    /**
     * Endpoint for user registration.
     * 
     * @return ResponseEntity containing the jwt token response.
     */
    @PostMapping("register")
    public ResponseEntity<AuthReponse> register(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            throw new BadRequestException("Un utilisateur avec cet email existe déjà.");
        }
        else if (authService.hasUserWithName(signupRequest.getUserName())) {
            throw new BadRequestException("Un utilisateur avec ce nom existe déjà.");
        }
        return authService.register(signupRequest);        
    }

    /*
     * Endpoint for user connexion.
     */
    @PostMapping("login")
    public ResponseEntity<AuthReponse> authenticateController(@RequestBody LoginRequest loginRequest) {
        try {
            return authService.authenticate(loginRequest);
        }
        catch (AuthenticationException e) {
            throw new UnauthorizedException("Email ou mot de passe incorrect.");
        }
    }

    /*
     * Endpoint to get the authenticated user's information from the jwt token.
     */
    @GetMapping("me")
    public ResponseEntity<UserDto> authenticatedUser() {
        UserDto response = userService.getAuthenticatedUserAuthMeReponse();
        return ResponseEntity.ok(response);
    }
}