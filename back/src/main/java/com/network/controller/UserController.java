package com.network.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.mapper.UserMapper;
import com.network.models.User;
import com.network.payload.request.UpdateUserRequest;
import com.network.payload.response.UserSuccessfullyUpdatedReponse;
import com.network.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controller for updating user information.
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to update user information.
     *
     * @param updates A map containing the fields to update and their new values.
     * @return ResponseEntity containing the new jwt token and a success message.
     */
    @PutMapping()
    public ResponseEntity<?> putMethodName(@RequestBody UpdateUserRequest updates) {
        UserSuccessfullyUpdatedReponse reponse = userService.updateUser(updates);
        return ResponseEntity.ok().body(reponse);
    }
}
