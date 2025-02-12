package com.network.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.models.User;
import com.network.service.UserService;

/**
 * usefull ???
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Constructor to inject UserService dependency
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to create a new user
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user.getEmail(), user.getUserName(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Test endpoint to return a simple string response
    @GetMapping("/test-request")
    public ResponseEntity<String> getUserById() {
        return ResponseEntity.status(HttpStatus.CREATED).body("someData");
    }
}
