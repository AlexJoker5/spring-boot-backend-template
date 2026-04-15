package com.main.java.features.address.service.impl;

import org.springframework.stereotype.Service;

import com.main.java.entity.Address;
import com.main.java.entity.UserInfo;
import com.main.java.features.address.dto.request.AddressRequest;
import com.main.java.features.address.dto.response.AddressResponse;
import com.main.java.features.address.mapper.AddressMapper;
import com.main.java.features.address.repository.AddressRepository;
import com.main.java.features.address.service.AddressService;
import com.main.java.repository.UserInfoRepository;
import com.main.java.service.impl.BaseServiceImpl;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, AddressRequest, AddressResponse >  implements AddressService{

	private final AddressMapper addressMapper;
	private final AddressRepository addressRepository;
	private final UserInfoRepository userInfoRepository;
	
	public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, UserInfoRepository userInfoRepository) {
		super(addressRepository);
		this.addressMapper = addressMapper;
		this.addressRepository = addressRepository;
		this.userInfoRepository = userInfoRepository;
	}

	@Override
	protected Address mapRequestToEntity(AddressRequest request) {
		UserInfo userInfo = findByIdOrThrow(request.userId(), userInfoRepository);
		return addressMapper.toEntity(request, userInfo);
	}

	@Override
	protected AddressResponse mapEntitytoResponse(Address entity) {
		return addressMapper.toResponse(entity);
	}

	@Override
	protected void updateEntityFromRequest(Address entity, AddressRequest request) {
		UserInfo userInfo = findByIdOrThrow(request.userId(), userInfoRepository);
		addressMapper.updateEntity(entity, request, userInfo);
	}

}
