package com.larvae.backend_template.features.address.dto.response;

import java.util.UUID;

import com.larvae.backend_template.enums.MyanmarRegion;
import com.larvae.backend_template.mapper.BaseData;

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
