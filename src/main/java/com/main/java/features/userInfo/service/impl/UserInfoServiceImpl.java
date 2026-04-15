package com.main.java.features.userInfo.service.impl;

import org.springframework.stereotype.Service;

import com.main.java.entity.Account;
import com.main.java.entity.UserInfo;
import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.dto.response.UserInfoResponse;
import com.main.java.features.userInfo.mapper.UserInfoMapper;
import com.main.java.features.userInfo.service.UserInfoService;
import com.main.java.repository.AccountRepository;
import com.main.java.repository.UserInfoRepository;
import com.main.java.service.impl.BaseServiceImpl;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, UserInfoRequest, UserInfoResponse>
		implements UserInfoService {

	private final UserInfoMapper userInfoMapper;
	private final UserInfoRepository userInfoRepository;
	private final AccountRepository accountRepository;

	protected UserInfoServiceImpl(UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper, AccountRepository accountRepository) {
		super(userInfoRepository);
		this.userInfoMapper = userInfoMapper;
		this.userInfoRepository = userInfoRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	protected UserInfo mapRequestToEntity(UserInfoRequest request) {
		Account account = findByIdOrThrow(request.accountId(), accountRepository);
		return userInfoMapper.toEntity(request, account);
	}

	@Override
	protected UserInfoResponse mapEntitytoResponse(UserInfo entity) {
		return userInfoMapper.toResponse(entity);
	}

	@Override
	protected void updateEntityFromRequest(UserInfo entity, UserInfoRequest request) {
		Account account = findByIdOrThrow(request.accountId(), accountRepository);
		userInfoMapper.updateEntity(entity, request, account);
	}
}
