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

    public List<Theme> findAllThemes() {
        return themeRepository.findAll();
    }   

    public List<Theme> getSubscribedThemes(Long userId) {
        User user = userService.findById(userId);
        return user.getThemes(); // Assurez-vous que la relation entre User et Theme est correctement configurée
    }
}
