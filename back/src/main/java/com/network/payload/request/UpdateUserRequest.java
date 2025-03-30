package com.network.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String email;
    private String userName;
    private String password;
}
