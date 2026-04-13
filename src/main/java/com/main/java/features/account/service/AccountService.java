package com.main.java.features.account.service;

import java.util.UUID;

import com.main.java.entity.Account;

public interface AccountService {

	void createUser(Account account);
	
	Account findByEmpId(UUID empId);

}
