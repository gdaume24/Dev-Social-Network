package com.network.dto;

import java.util.Set;

import com.network.models.Theme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto to map sensitive user data when returning from controller
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String userName;
    private Set<Theme> themes;
    
}