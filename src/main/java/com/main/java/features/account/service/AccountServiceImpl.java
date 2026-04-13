package com.main.java.features.account.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.java.entity.Account;
import com.main.java.repository.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public void createUser(Account account) {
		accountRepo.save(account);
	}
	
}
