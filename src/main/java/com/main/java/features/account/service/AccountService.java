package com.main.java.features.account.service;

import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.dto.response.AccountResponse;
import com.main.java.service.BaseService;

/**
 * Service interface for account business operations.
 */
public interface AccountService extends BaseService<AccountRequest, AccountResponse> {

    /**
     * Find an account by username.
     *
     * @param username account username
     * @return matching account response
     */
    AccountResponse findByUsername(String username);

}
