package com.network.services;


import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.network.dto.UserDto;
import com.network.mapper.UserMapper;
import com.network.models.Article;
import com.network.models.Theme;
import com.network.models.User;
import com.network.repository.ArticleRepository;
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
    private final ArticleRepository articleRepository;

    public UserService(UserRepository userRepository, ThemeRepository themeRepository, UserMapper userMapper, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
        this.userMapper = userMapper;
        this.articleRepository = articleRepository;
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

    public List<Article> getSubscribedArticles(Long userId) {
        
        User user = userRepository.findById(userId).orElseThrow();

        // Récupère la liste des articles des thèmes auxquels l'utilisateur est abonné
        List<Theme> subscribedThemes = user.getThemes();
        List<Article> filtredArticleList = articleRepository.findByThemeIn(subscribedThemes);
        // Trie les articles du plus récent au plus ancien
        filtredArticleList.sort(Comparator.comparing(Article::getDate).reversed());

        return filtredArticleList;
        
    }

        public User getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User currentUser = (User) authentication.getPrincipal();
            return currentUser;
        }

        throw new RuntimeException("Utilisateur non authentifié");
    }

    public UserDto getAuthenticatedUserAuthMeReponse() {

        User currentUser = getAuthenticatedUser();
        UserDto response = userMapper.toDto(currentUser);
        
        return response;
        }
}
