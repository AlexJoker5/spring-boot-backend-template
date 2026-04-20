package com.larvae.backend_template.features.userInfo.service.impl;

import org.springframework.stereotype.Service;

import com.larvae.backend_template.entity.Account;
import com.larvae.backend_template.entity.UserInfo;
import com.larvae.backend_template.features.userInfo.dto.request.UserInfoRequest;
import com.larvae.backend_template.features.userInfo.dto.response.UserInfoResponse;
import com.larvae.backend_template.features.userInfo.mapper.UserInfoMapper;
import com.larvae.backend_template.features.userInfo.service.UserInfoService;
import com.larvae.backend_template.features.auth.repository.AccountRepository;
import com.larvae.backend_template.features.userInfo.repository.UserInfoRepository;
import com.larvae.backend_template.service.impl.BaseServiceImpl;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, UserInfoRequest, UserInfoResponse>
		implements UserInfoService {

	private final UserInfoMapper userInfoMapper;
	private final AccountRepository accountRepository;

	protected UserInfoServiceImpl(UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper, AccountRepository accountRepository) {
		super(userInfoRepository);
		this.userInfoMapper = userInfoMapper;
		this.accountRepository = accountRepository;
	}

	@Override
	protected UserInfo mapRequestToEntity(UserInfoRequest request) {
		Account account = findByIdOrThrow(request.accountId(), accountRepository);
		return userInfoMapper.toEntity(request, account);
	}

	@Override
	protected UserInfoResponse mapEntityToResponse(UserInfo entity) {
		return userInfoMapper.toResponse(entity);
	}

	@Override
	protected void updateEntityFromRequest(UserInfo entity, UserInfoRequest request) {
		Account account = findByIdOrThrow(request.accountId(), accountRepository);
		userInfoMapper.updateEntity(entity, request, account);
	}
}
