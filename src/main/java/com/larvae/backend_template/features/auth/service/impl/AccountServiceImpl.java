package com.larvae.backend_template.features.auth.service.impl;

import com.larvae.backend_template.enums.AccountStatus;
import com.larvae.backend_template.features.auth.dto.request.AccountRequest;
import com.larvae.backend_template.features.auth.dto.response.AccountResponse;
import com.larvae.backend_template.features.auth.mapper.AccountMapper;
import com.larvae.backend_template.features.auth.service.AccountService;
import com.larvae.backend_template.service.impl.BaseServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.larvae.backend_template.entity.Account;
import com.larvae.backend_template.features.auth.repository.AccountRepository;

/**
 * Implementation of auth service operations.
 */
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
		account.setPassword(passwordEncoder.encode(request.password()));
		account.setStatus(AccountStatus.ACTIVE);
		Account savedAccount = accountRepo.save(account);
		return mapEntityToResponse(savedAccount);
	}

	@Override
	protected Account mapRequestToEntity(AccountRequest request) {
		return accountMapper.toEntity(request);
	}

	@Override
	protected AccountResponse mapEntityToResponse(Account entity) {
		return accountMapper.toResponse(entity);
	}

	@Override
	protected void updateEntityFromRequest(Account entity, AccountRequest request) {
		accountMapper.updateEntity(entity, request);
		if (request.password() != null && !request.password().isBlank()) {
			entity.setPassword(passwordEncoder.encode(request.password()));
		}
	}

	@Override
	public AccountResponse findByUsername(String username) {
		Account account = accountRepo.findByUsernameAndIsActiveTrue(username)
				.orElseThrow(() ->
						new UsernameNotFoundException("User Not Found with username: " + username )
				);
		return accountMapper.toResponse(account);
	}
}
