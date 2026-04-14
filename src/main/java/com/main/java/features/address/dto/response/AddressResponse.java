package com.main.java.features.address.dto.response;

import java.util.UUID;

import com.main.java.enums.MyanmarRegion;
import com.main.java.mapper.BaseData;

import lombok.Builder;

@Builder
public record AddressResponse(
		MyanmarRegion region,
		
		String township,
		
		String postalCode,
		
		String fullAddress,
		
		UUID userId,
		
		BaseData baseData
		) {

}
