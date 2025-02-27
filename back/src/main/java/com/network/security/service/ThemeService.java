package com.network.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.network.models.Theme;
import com.network.repository.ThemeRepository;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<Theme> findAllThemes() {
        return themeRepository.findAll();
    }   
}
