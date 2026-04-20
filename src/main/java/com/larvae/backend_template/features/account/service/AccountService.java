package com.larvae.backend_template.features.account.service;

import com.larvae.backend_template.features.account.dto.request.AccountRequest;
import com.larvae.backend_template.features.account.dto.response.AccountResponse;
import com.larvae.backend_template.service.BaseService;

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
