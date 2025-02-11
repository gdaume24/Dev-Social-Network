package com.network.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String userName;

	@NotBlank
	private String password;
}
