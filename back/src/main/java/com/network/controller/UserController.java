package com.network.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.dto.UserDto;
import com.network.mapper.UserMapper;
import com.network.models.User;
import com.network.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

/**
 * For subscribing to themes, getting subscribed articles and updating user information
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            User user = this.userService.findById(Long.valueOf(id));
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(this.userMapper.toDto(user));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable String id, @Valid @RequestBody UserDto userDto) {
        User user = userService.updateById(Long.valueOf(id), this.userMapper.toEntity(userDto));
        return ResponseEntity.ok().body(this.userMapper.toDto(user));
    }
}
