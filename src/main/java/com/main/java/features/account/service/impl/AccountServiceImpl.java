package com.main.java.features.account.service.impl;

import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.dto.response.AccountResponse;
import com.main.java.features.account.mapper.AccountMapper;
import com.main.java.features.account.service.AccountService;
import com.main.java.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.main.java.entity.Account;
import com.main.java.repository.AccountRepository;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountRequest, AccountResponse> implements AccountService {

	private final AccountRepository accountRepo;
	private final AccountMapper accountMapper;

	public AccountServiceImpl(AccountRepository accountRepo, AccountMapper accountMapper) {
        super(accountRepo);
		this.accountRepo = accountRepo;
		this.accountMapper = accountMapper;
	}

	@Override
	protected Account mapRequestToEntity(AccountRequest request) {
		return accountMapper.toEntity(request);
	}

	@Override
	protected AccountResponse mapEntityToResponse(Account entity) {
		return accountMapper.toResponseDto(entity);
	}

	@Override
	protected void updateEntityFromRequest(AccountRequest request, Account entity) {
		accountMapper.updateEntityFromRequestDto(request, entity);
	}
}
