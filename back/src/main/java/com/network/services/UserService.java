package com.network.services;


import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.network.dto.UserDto;
import com.network.mapper.UserMapper;
import com.network.models.Theme;
import com.network.models.User;
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

    public UserService(UserRepository userRepository, ThemeRepository themeRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
        this.userMapper = userMapper;
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

    public User updateById(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
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
}
