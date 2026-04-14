package com.main.java.features.userInfo.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
public record UserInfoRequest (
		@NotEmpty(message = "First Name must not be empty")
		@Size(min = 2, max = 50, message = "First Name must be between 2 and 50 characters")
		String firstName,

		@NotEmpty(message = "Last Name must not be empty")
		@Size(min = 2, max = 50, message = "Last Name must be between 2 and 50 characters")
		String lastName,

		@NotNull(message = "Join Date must not be empty")
		LocalDateTime joinDate,

		LocalDateTime resignDate,

		@NotNull(message = "Account Id must not be empty")
		UUID accountId
){
}
