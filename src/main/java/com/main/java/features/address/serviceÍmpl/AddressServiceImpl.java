package com.main.java.features.address.serviceÍmpl;

import org.springframework.stereotype.Service;

import com.main.java.common.RepoHelper;
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

	private final AddressRepository addressRepo;
	private final UserInfoRepository userInfoRepo;
	private final AddressMapper addressMapper;
	
	public AddressServiceImpl(AddressRepository addressRepo, AddressMapper addressMapper, UserInfoRepository userInfoRepo) {
		super(addressRepo);
		this.addressRepo = addressRepo;
		this.addressMapper = addressMapper;
		this.userInfoRepo = userInfoRepo;
		
	}
	
	

//	@Override
//	public AddressResponse findByTownship(String township) {
//		Address address = addressRepo.findByTownship(township);
//		return null;
//	}

	@Override
	protected Address mapRequestToEntity(AddressRequest request) {
		UserInfo userInfo = RepoHelper.findByIdOrThrow(userInfoRepo, request.userId(), "Address", "address id");
		return addressMapper.toEntity(request, userInfo);
	}

	@Override
	protected AddressResponse mapEntitytoResponse(Address entity) {
		return addressMapper.toResponse(entity);
	}

	@Override
	protected void updateEntityFromRequest(Address entity, AddressRequest request) {
		UserInfo userInfo = RepoHelper.findByIdOrThrow(userInfoRepo, request.userId(), "Address", "address id");
			addressMapper.updateEntity(entity, request, userInfo);
	}

}
