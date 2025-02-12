package com.network.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class Test {
    
    // Test endpoint to return a simple string response
    @GetMapping("/test-request")
    public ResponseEntity<String> getUserById() {
        return ResponseEntity.status(HttpStatus.CREATED).body("someData");
    }

}
