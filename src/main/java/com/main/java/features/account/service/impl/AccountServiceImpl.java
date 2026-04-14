package com.main.java.features.account.service.impl;

import com.main.java.common.CommonConstants;
import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.dto.response.AccountResponse;
import com.main.java.features.account.mapper.AccountMapper;
import com.main.java.features.account.service.AccountService;
import com.main.java.service.impl.BaseServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.java.entity.Account;
import com.main.java.repository.AccountRepository;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountRequest, AccountResponse> implements AccountService {

	private final AccountRepository accountRepo;
	private final AccountMapper accountMapper;
	private final PasswordEncoder passwordEncoder;

	public AccountServiceImpl(AccountRepository accountRepo, AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        super(accountRepo);
		this.accountRepo = accountRepo;
		this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public AccountResponse create(AccountRequest request) {
		Account account = mapRequestToEntity(request);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setStatus(CommonConstants.IS_ACTIVE);
		Account savedAccount = accountRepo.save(account);
		return mapEntitytoResponse(savedAccount);
	}

	@Override
	protected Account mapRequestToEntity(AccountRequest request) {
		return accountMapper.toEntity(request);
	}

	@Override
	protected AccountResponse mapEntitytoResponse(Account entity) {
		return accountMapper.toResponse(entity);
	}

	@Override
	protected void updateEntityFromRequest(Account entity, AccountRequest request) {
		accountMapper.updateEntity(entity, request);
	}

	@Override
	public AccountResponse findByUsername(String username) {
		Account account = accountRepo.findByUsername(username);
		return accountMapper.toResponse(account);
	}
}
