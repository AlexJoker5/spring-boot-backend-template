package com.main.java.features.address.dto.request;

import java.util.UUID;

import com.main.java.enums.MyanmarRegion;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AddressRequest(
		
		@NotNull(message = "Region must not be empty!")
		MyanmarRegion region,
		
		@NotEmpty(message = "Township must not be empty")
		@Size(min = 5, max = 100, message = "Township name must be between 5 and 100!")
		String township,
		
		@NotEmpty(message = "Postal code must not be empty!")
		@Pattern( regexp = "^[0-9]{5}$", message = "Postal code must be 5 digits!")
		String postalCode,
		
		@NotNull(message = "User id must not be empty!")
		UUID userId,
		
		@NotEmpty(message= "Full address must not be empty!")
		@Size(min = 20, max = 300, message = "Full address must be at least 20!")
		String fullAddress
		
		) {
	

}
