package com.network.services;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.network.dto.UserDto;
import com.network.mapper.UserMapper;
import com.network.models.Theme;
import com.network.models.User;
import com.network.payload.request.UpdateUserRequest;
import com.network.payload.response.UserSuccessfullyUpdatedReponse;
import com.network.repository.ThemeRepository;
import com.network.repository.UserRepository;

/**
 * Service for user operations
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
        UserRepository userRepository, 
        ThemeRepository themeRepository,
        UserMapper userMapper,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
        ) {
            this.userRepository = userRepository;
            this.themeRepository = themeRepository;
            this.userMapper = userMapper;
            this.passwordEncoder = passwordEncoder;
            this.jwtService = jwtService;
        }

    public User createUser(String email, String userName, String password) {
        User user = User.builder()
                .email(email)
                .userName(userName)
                .password(password)
                .build();
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserSuccessfullyUpdatedReponse updateUser(UpdateUserRequest updates) {
        User user = getAuthenticatedUser();

        // Met à jour uniquement les champs non nuls
        if (updates.getUserName() != null) {
            user.setUserName(updates.getUserName());
        }
        if (updates.getEmail() != null) {
            user.setEmail(updates.getEmail());
        }
        if (updates.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updates.getPassword()));
        }

        User updatedUser = userRepository.save(user);

        // Générer un nouveau JWT avec les nouvelles informations de l'utilisateur
        String newJwt = jwtService.generateToken(updatedUser);

        // Met à jour le SecurityContext
        updateSecurityContext(updatedUser);

        UserSuccessfullyUpdatedReponse response = new UserSuccessfullyUpdatedReponse();
        response.setJwt(newJwt);
        response.setMessage("User updated successfully");
        return response;
    }

    public User subscribeToTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow();
        Theme theme = themeRepository.findById(themeId).orElseThrow();
        user.getThemes().add(theme);
        return userRepository.save(user);
    }

    public User unsubscribeFromTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setThemes(user.getThemes().stream().filter(userTheme -> !userTheme.getId().equals(themeId)).collect(Collectors.toList()));
        return userRepository.save(user);
    }

    public boolean isSubscribedToTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getThemes().stream().anyMatch(theme -> theme.getId().equals(themeId));
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        throw new RuntimeException("Utilisateur non authentifié");
    }

    public UserDto getAuthenticatedUserAuthMeReponse() {
        User currentUser = getAuthenticatedUser();
        UserDto response = userMapper.toDto(currentUser);
        return response;
    }

    private void updateSecurityContext(User updatedUser) {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            updatedUser.getUserName(),
            updatedUser.getPassword(),
            updatedUser.getAuthorities()
        );
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
