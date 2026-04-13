package com.main.java.features.account.dto.request;

import com.main.java.common.CommonConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountRequest {

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
    )
    private String password;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(
            regexp = "^[0-9]{2}$",
            message = "This role is invalid"
    )
    private String role;

    private String status;

}
