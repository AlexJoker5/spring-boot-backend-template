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
        super(accountRepo, accountMapper);
		this.accountRepo = accountRepo;
		this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public AccountResponse create(AccountRequest request) {
		Account account = accountMapper.toEntity(request);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setStatus(CommonConstants.IS_ACTIVE);
		Account savedAccount = accountRepo.save(account);
		return accountMapper.toResponseDto(savedAccount);
	}

	@Override
	public AccountResponse findByUsername(String username) {
		Account account = accountRepo.findByUsername(username);
		return accountMapper.toResponseDto(account);
	}
}
