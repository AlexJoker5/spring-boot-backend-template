package com.larvae.backend_template.features.address.service.impl;

import org.springframework.stereotype.Service;

import com.larvae.backend_template.entity.Address;
import com.larvae.backend_template.entity.UserInfo;
import com.larvae.backend_template.features.address.dto.request.AddressRequest;
import com.larvae.backend_template.features.address.dto.response.AddressResponse;
import com.larvae.backend_template.features.address.mapper.AddressMapper;
import com.larvae.backend_template.features.address.repository.AddressRepository;
import com.larvae.backend_template.features.address.service.AddressService;
import com.larvae.backend_template.features.userInfo.repository.UserInfoRepository;
import com.larvae.backend_template.service.impl.BaseServiceImpl;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, AddressRequest, AddressResponse >  implements AddressService{

	private final AddressMapper addressMapper;
	private final UserInfoRepository userInfoRepository;
	
	public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, UserInfoRepository userInfoRepository) {
		super(addressRepository);
		this.addressMapper = addressMapper;
		this.userInfoRepository = userInfoRepository;
	}

	@Override
	protected Address mapRequestToEntity(AddressRequest request) {
		UserInfo userInfo = findByIdOrThrow(request.userId(), userInfoRepository);
		return addressMapper.toEntity(request, userInfo);
	}

	@Override
	protected AddressResponse mapEntityToResponse(Address entity) {
		return addressMapper.toResponse(entity);
	}

	@Override
	protected void updateEntityFromRequest(Address entity, AddressRequest request) {
		UserInfo userInfo = findByIdOrThrow(request.userId(), userInfoRepository);
		addressMapper.updateEntity(entity, request, userInfo);
	}

}
