package com.csOneCup.csOneCup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "User ID is required")
    @Size(max = 30, message = "User ID cannot exceed 30 characters")
    @JsonProperty("user_id")
    private String userId;

    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Name cannot exceed 30 characters")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Password is required")
    @Size(min = 30, message = "Password must be at least 30 characters long")
    @JsonProperty("password")
    private String password;
}
