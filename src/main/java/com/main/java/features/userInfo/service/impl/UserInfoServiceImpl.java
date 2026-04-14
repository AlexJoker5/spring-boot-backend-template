package com.main.java.features.userInfo.service.impl;

import com.main.java.entity.Account;
import com.main.java.repository.AccountRepository;
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

	private final UserInfoMapper userInfoMapper;

	protected UserInfoServiceImpl(UserInfoRepository userInfoRepo, UserInfoMapper userInfoMapper) {
		super(userInfoRepo);
		this.userInfoMapper = userInfoMapper;
	}

	@Override
	protected UserInfo mapRequestToEntity(UserInfoRequest request) {
		Account account = findByIdOrThrow(request.accountId()).getAccount();
		return userInfoMapper.toEntity(request, account);
	}

	@Override
	protected UserInfoResponse mapEntitytoResponse(UserInfo entity) {
		return userInfoMapper.toResponse(entity);
	}

	@Override
	protected void updateEntityFromRequest(UserInfo entity, UserInfoRequest request) {
		Account account = findByIdOrThrow(request.accountId()).getAccount();
		userInfoMapper.updateEntity(entity, request, account);
	}
}
