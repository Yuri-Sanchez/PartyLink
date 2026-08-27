package com.partylink.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 150, message = "Name must have at most 150 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        @Size(max = 255, message = "Email must have at most 255 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 255, message = "Password must have between 8 and 100 characters")
        String password,

        @Size(max = 20, message = "Phone must have at most 20 characters")
        String phone,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth
) {
}
