package com.main.java.features.address.mapper;

import org.springframework.stereotype.Component;

import com.main.java.entity.Address;
import com.main.java.entity.UserInfo;
import com.main.java.features.address.dto.request.AddressRequest;
import com.main.java.features.address.dto.response.AddressResponse;
import com.main.java.mapper.BaseDataMapper;

@Component
public class AddressMapper {
	
	private final BaseDataMapper baseDataMapper;
	
	public AddressMapper(BaseDataMapper baseDataMapper) {
		
		this.baseDataMapper = baseDataMapper;
		
	}
	
	public Address toEntity(AddressRequest request, UserInfo userInfo) {
		Address entity = new Address();
		setRequestEntity(entity, request, userInfo);
		return entity;
	}
	
	public AddressResponse toResponse(Address entity) {
		
		if(entity == null) return null;
		return AddressResponse.builder()
				.region(entity.getRegion())
				.township(entity.getTownship())
				.postalCode(entity.getPostalCode())
				.fullAddress(entity.getFullAddress())
				.userId(entity.getUserInfo().getId())
				.baseData(baseDataMapper.toBasicData(entity))
				.build();
		}
	
	public void updateEntity(Address entity, AddressRequest request, UserInfo userInfo) {
		setRequestEntity(entity, request, userInfo);
		
	}
	
	public void setRequestEntity(Address entity, AddressRequest request, UserInfo userInfo) {
		
		entity.setRegion(request.region());
		entity.setTownship(request.township());
		entity.setFullAddress(request.fullAddress());
		entity.setPostalCode(request.postalCode());
		entity.setUserInfo(userInfo);
		
	}
	

}
