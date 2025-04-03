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

    /*
     * Récupère l'utilisateur authentifié avec la clef Jwt, mets à jour l'objet User dans la base de données si les champs de l'objet de requête sont non nulls.
     * Génère un nouveau JWT avec les nouvelles informations de l'utilisateur et met à jour le SecurityContext, sinon la clef jwt ne sera plus à jour avec les informations de l'utilisateur.
     */
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

    /*
     * Ajoute un thème à l'utilisateur authentifié.
     */
    public User subscribeToTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow();
        Theme theme = themeRepository.findById(themeId).orElseThrow();
        user.getThemes().add(theme);
        return userRepository.save(user);
    }

    /* 
     * Enlève un thème à l'utilisateur authentifié.
     */
    public User unsubscribeFromTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setThemes(user.getThemes().stream().filter(userTheme -> !userTheme.getId().equals(themeId)).collect(Collectors.toList()));
        return userRepository.save(user);
    }

    /*
     * Retourne un boolean selon si l'utilisateur est abonné au thème ou non.
     */
    public boolean isSubscribedToTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getThemes().stream().anyMatch(theme -> theme.getId().equals(themeId));
    }

    /**
     * Récupère l'utilisateur actuellement authentifié à partir du SecurityContext.
     *
     * @return L'utilisateur authentifié sous forme d'objet User.
     * @throws RuntimeException si aucun utilisateur n'est authentifié.
     */
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        throw new RuntimeException("Utilisateur non authentifié");
    }

    /**
     * Récupère les informations de l'utilisateur authentifié et les retourne sous forme de DTO.
     *
     * @return Un objet UserDto contenant les informations de l'utilisateur authentifié.
     */
    public UserDto getAuthenticatedUserAuthMeReponse() {
        User currentUser = getAuthenticatedUser();
        UserDto response = userMapper.toDto(currentUser);
        return response;
    }

    /**
     * Met à jour le SecurityContext avec les informations d'un utilisateur mis à jour.
     * Cela garantit que le SecurityContext reflète les dernières modifications de l'utilisateur,
     * comme un changement de nom d'utilisateur ou de mot de passe.
     *
     * @param updatedUser L'utilisateur mis à jour dont les informations doivent être reflétées dans le SecurityContext.
     */
    private void updateSecurityContext(User updatedUser) {
        // Crée un nouvel objet UserDetails avec les informations mises à jour
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            updatedUser.getUserName(),
            updatedUser.getPassword(),
            updatedUser.getAuthorities()
        );
        // Crée un nouvel objet Authentication avec les informations mises à jour
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // Met à jour le SecurityContext avec le nouvel objet Authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
