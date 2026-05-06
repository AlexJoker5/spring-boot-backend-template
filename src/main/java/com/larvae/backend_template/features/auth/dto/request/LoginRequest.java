package com.larvae.backend_template.features.auth.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Request payload for user login.
 *
 * @param username the user username
 * @param password the user password
 */
public record LoginRequest(
        @NotEmpty(message = "Username must not be empty")
        @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")
        String username,

        @NotEmpty(message = "Password must not be empty")
        String password
) {
}
