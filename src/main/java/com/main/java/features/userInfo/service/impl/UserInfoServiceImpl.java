package com.main.java.features.userInfo.service.impl;

import com.main.java.entity.Account;
import com.main.java.features.account.service.AccountService;
import com.main.java.repository.AccountRepository;
import com.main.java.service.impl.BaseServiceImpl;
import jakarta.persistence.EntityNotFoundException;
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
	private final AccountRepository accountRepo;
	private final UserInfoMapper userInfoMapper;

	protected UserInfoServiceImpl(UserInfoRepository userInfoRepo, AccountRepository accountRepo, UserInfoMapper userInfoMapper) {
		super(userInfoRepo, userInfoMapper);
		this.userInfoRepo = userInfoRepo;
		this.accountRepo = accountRepo;
		this.userInfoMapper = userInfoMapper;
	}

//	@Override
//	protected UserInfo mapRequestToEntity(UserInfoRequest request) {
//		UserInfo userInfo = userInfoMapper.toEntity(request);
//		if(request.getAccountId() != null) {
//			Account account = accountRepo.findById(request.getAccountId())
//					.orElseThrow(() -> new EntityNotFoundException(
//							"Account not found with id: " + request.getAccountId()
//					));
//			userInfo.setAccountId(account);
//		}
//		return userInfo;
//	}
//
//	@Override
//	protected UserInfoResponse mapEntityToResponse(UserInfo entity) {
//		return userInfoMapper.toResponseDto(entity);
//	}
//
//	@Override
//	protected void updateEntityFromRequest(UserInfoRequest request, UserInfo entity) {
//		userInfoMapper.updateEntityFromRequestDto(request, entity);
//	}
}
