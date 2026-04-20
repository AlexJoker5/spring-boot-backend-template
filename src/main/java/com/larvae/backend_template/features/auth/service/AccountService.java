package com.larvae.backend_template.features.auth.service;

import com.larvae.backend_template.features.auth.dto.request.AccountRequest;
import com.larvae.backend_template.features.auth.dto.response.AccountResponse;
import com.larvae.backend_template.service.BaseService;

/**
 * Service interface for auth business operations.
 */
public interface AccountService extends BaseService<AccountRequest, AccountResponse> {

    /**
     * Find an auth by username.
     *
     * @param username auth username
     * @return matching auth response
     */
    AccountResponse findByUsername(String username);

}
