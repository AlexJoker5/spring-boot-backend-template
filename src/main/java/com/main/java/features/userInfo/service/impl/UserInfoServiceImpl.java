package com.main.java.features.userInfo.service.impl;

import com.main.java.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.main.java.entity.UserInfo;
import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.dto.response.UserInfoResponse;
import com.main.java.features.userInfo.mapper.UserInfoMapper;
import com.main.java.features.userInfo.service.UserInfoService;
import com.main.java.repository.UserInfoRepository;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, UserInfoRequest, UserInfoResponse>
		implements UserInfoService {

	private final UserInfoRepository userInfoRepo;
//	private final AccountRepo accountRepo;
//	private final BCryptPasswordEncoder passwordEncoder;
	private final UserInfoMapper mapper;

	protected UserInfoServiceImpl(UserInfoRepository userInfoRepo, UserInfoMapper mapper) {
		super(userInfoRepo);
		this.userInfoRepo = userInfoRepo;
//		this.accountRepo = accountRepo;
//		this.passwordEncoder = passwordEncoder;
		this.mapper = mapper;
	}

	@Override
	protected UserInfo mapRequestToEntity(UserInfoRequest request) {
		return mapper.toEntity(request);
	}

	@Override
	protected UserInfoResponse mapEntityToResponse(UserInfo entity) {
		return mapper.toResponseDto(entity);
	}

	@Override
	protected void updateEntityFromRequest(UserInfoRequest request, UserInfo entity) {
		mapper.updateEntityFromRequestDto(request, entity);
	}
}
