package com.larvae.backend_template.features.auth.dto.request;

import com.larvae.backend_template.enums.AccountStatus;
import com.larvae.backend_template.enums.Roles;
import com.larvae.backend_template.features.auth.validation.ValidateAccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Request payload used to create or update an auth.
 *
 * @param username the auth username
 * @param password the auth password
 * @param role the auth role code
 * @param status the auth status flag
 */
@Builder
@ValidateAccountType
public record AccountRequest (
        @NotEmpty(message = "Username must not be empty")
        @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")
        String username,

        @NotEmpty(message = "Password must not be empty")
        @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=])(?=\\S+$).{8,}$",
                message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
        )
        String password,

        @NotNull(message = "Role must not be empty")
        Roles role,

        @NotNull(message = "Status must not be empty")
        AccountStatus status
){

}
