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

    @PostMapping("login")
    public ResponseEntity<AuthReponse> authenticate(@RequestBody LoginRequest loginRequest) {

        try {
            return authService.authenticate(loginRequest);
        }
        catch (AuthenticationException e) {
            throw new UnauthorizedException("Email ou mot de passe incorrect.");
        }
    }

    @GetMapping("me")
    public ResponseEntity<UserDto> authenticatedUser() {

        UserDto response = userService.getAuthenticatedUserAuthMeReponse();
        
        return ResponseEntity.ok(response);
    }
}