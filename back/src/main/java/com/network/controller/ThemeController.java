package com.network.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.mapper.ThemeMapper;
import com.network.mapper.UserMapper;
import com.network.models.Theme;
import com.network.models.User;
import com.network.services.ThemeService;
import com.network.services.UserService;

/**
 * Controller to manage themes, allowing users to view, subscribe, or unsubscribe from themes.
 */
@RestController
@RequestMapping("/themes")
public class ThemeController {
    
    private final ThemeService themeService; 
    private final ThemeMapper themeMapper;  
    private final UserService userService;   
    private final UserMapper userMapper;    

    ThemeController(ThemeService themeService, ThemeMapper themeMapper, UserService userService, UserMapper userMapper) {
        this.themeService = themeService;
        this.themeMapper = themeMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Fetches all available themes.
     * @return ResponseEntity containing a list of all themes.
     */
    @GetMapping("")
    public ResponseEntity<?> findAllThemes() {
        List<Theme> themes = themeService.findAllThemes();
        return ResponseEntity.ok().body(themeMapper.toDto(themes));
    }

    /**
     * Fetches themes the authenticated user is subscribed to.
     * @return ResponseEntity containing a list of subscribed themes.
     */
    @GetMapping("/subscribed")
    public ResponseEntity<?> getSubscribedThemes() {
        Long userId = userService.getAuthenticatedUser().getId();
        List<Theme> subscribedThemes = themeService.getSubscribedThemes(userId);
        return ResponseEntity.ok().body(themeMapper.toDto(subscribedThemes));
    }

    /**
     * Subscribes the authenticated user to a theme.
     * @param themeId ID of the theme to subscribe to.
     * @return ResponseEntity containing the updated user information.
     */
    @PostMapping("/subscribe/{themeId}")
    public ResponseEntity<?> subscribe(@PathVariable("themeId") String themeId) {
        Long userId = userService.getAuthenticatedUser().getId();
        User user = userService.subscribeToTheme(userId, Long.parseLong(themeId));
        return ResponseEntity.ok().body(this.userMapper.toDto(user));
    }

    /**
     * Unsubscribes the authenticated user from a theme.
     * @param themeId ID of the theme to unsubscribe from.
     * @return ResponseEntity containing the updated user information.
     */
    @DeleteMapping("/unsubscribe/{themeId}")
    public ResponseEntity<?> unsubscribe(@PathVariable("themeId") String themeId) {
        Long userId = userService.getAuthenticatedUser().getId();
        User user = userService.unsubscribeFromTheme(userId, Long.parseLong(themeId));
        return ResponseEntity.ok().body(this.userMapper.toDto(user));
    }

    /**
     * Checks if the authenticated user is subscribed to a specific theme.
     * @param themeId ID of the theme to check.
     * @return ResponseEntity containing a boolean indicating subscription status.
     */
    @GetMapping("/isSubscribed/{themeId}")
    public ResponseEntity<Boolean> isSubscribedToTheme(@PathVariable Long themeId) {
        Long userId = userService.getAuthenticatedUser().getId();
        boolean isSubscribed = userService.isSubscribedToTheme(userId, themeId);
        return ResponseEntity.ok(isSubscribed);
    }
}
