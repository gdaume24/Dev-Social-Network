package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user.getEmail(), user.getUserName(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/create")
    public ResponseEntity<String> getData() {
        // User newUser = userService.createUser(user.getEmail(), user.getUserName(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("someData");
    }
}
