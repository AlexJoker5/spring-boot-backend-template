package com.main.java.features.userInfo.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class UserInfoRequest {

	/*
	 * Employee Status (E.g. Active, Resigned, Terminated)
	 */
	@NotEmpty(message = "Status must not be empty")
	@Size(max = 15, message = "Status length must not be greater than 15")
	private String status;

	@NotNull(message = "Join Date must not be empty")
	private LocalDateTime joinDate;
	
	private LocalDateTime resignDate;

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

}
