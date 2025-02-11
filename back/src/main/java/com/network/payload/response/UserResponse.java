package com.network.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserResponse {
    
    private Long id;

    private String email;

	private String userName;
}

