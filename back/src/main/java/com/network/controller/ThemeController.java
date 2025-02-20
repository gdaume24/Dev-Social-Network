package com.network.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.mapper.ThemeMapper;
import com.network.models.Theme;
import com.network.service.ThemeService;

/**
 * Controller to show all themes, subscribe or unsubscribe from themes
 */
@RestController
@CrossOrigin
@RequestMapping("/themes")
public class ThemeController {
    
    private final ThemeService themeService;
    private final ThemeMapper themeMapper;

    ThemeController(ThemeService themeService, ThemeMapper themeMapper) {
        this.themeService = themeService;
        this.themeMapper = themeMapper;
    }

    @GetMapping
    public ResponseEntity<?> findAllThemes() {
        List<Theme> themes = themeService.findAllThemes();
        return ResponseEntity.ok().body(themeMapper.toDto(themes));
    }
}
