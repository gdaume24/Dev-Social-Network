package com.network.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.network.models.Theme;
import com.network.models.User;
import com.network.repository.ThemeRepository;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final UserService userService;

    public ThemeService(ThemeRepository themeRepository, UserService userService) {
        this.userService = userService;
        this.themeRepository = themeRepository;
    }

    /*
     * Récupère tous les thèmes.
     */
    public List<Theme> findAllThemes() {
        return themeRepository.findAll();
    }   

    /*
     * Récupère tous les thèmes auxquels l'utilisateur est abonné.
     */
    public List<Theme> getSubscribedThemes(Long userId) {
        User user = userService.findById(userId);
        return user.getThemes();
    }
}
