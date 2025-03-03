package com.network.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.network.models.User;
import com.network.payload.request.LoginRequest;
import com.network.payload.request.SignupRequest;
import com.network.payload.response.AuthReponse;
import com.network.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ResponseEntity<AuthReponse> register(SignupRequest signupRequest) {
        User user = new User()
                .setUserName(signupRequest.getUserName())
                .setEmail(signupRequest.getEmail())
                .setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        User registeredUser = userRepository.save(user);
        AuthReponse authReponse = createAuthReponse(registeredUser);
        
        return ResponseEntity.ok(authReponse);
    }

    public ResponseEntity<AuthReponse> authenticate(LoginRequest loginRequestDto) {
        
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequestDto.getEmail(),
                    loginRequestDto.getPassword()
                )
            );
            User authenticatedUser = (User) authentication.getPrincipal();
            AuthReponse authReponse = createAuthReponse(authenticatedUser);

            return ResponseEntity.ok(authReponse); 
        }


        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private AuthReponse createAuthReponse(User user) {
        String jwtToken = jwtService.generateToken(user);
        return new AuthReponse().setToken(jwtToken);
    }

    public boolean hasUserWithEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    
    public boolean hasUserWithName(String name) {
        return userRepository.findByUserName(name).isPresent();
    }
}
