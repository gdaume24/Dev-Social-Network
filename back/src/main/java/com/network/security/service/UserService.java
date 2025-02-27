package com.network.security.service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.network.dto.ArticleDto;
import com.network.mapper.ArticleMapper;
import com.network.models.Article;
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

    public UserService(UserRepository userRepository, ThemeRepository themeRepository) {
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
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
        user.setThemes(user.getThemes().stream().filter(userTheme -> !userTheme.getId().equals(themeId)).collect(Collectors.toSet()));

        return userRepository.save(user);
    }

    public List<Article> getSubscribedArticles(Long userId) {
        
        User user = userRepository.findById(userId).orElseThrow();

        // Récupère la liste des articles des thèmes auxquels l'utilisateur est abonné, puis la trie du plus récent au plus anciens
        List<Article> filtredArticleList = user.getThemes().stream()
            .flatMap(theme -> theme.getArticles().stream())
            .distinct()
            .sorted(Comparator.comparing(Article::getDate).reversed())
            .collect(Collectors.toList());

        return filtredArticleList;
        
    }
}
