package com.larvae.backend_template.features.account.dto.response;

import com.larvae.backend_template.enums.AccountStatus;
import com.larvae.backend_template.enums.Roles;
import com.larvae.backend_template.mapper.BaseData;

import lombok.Builder;

/**
 * Response payload returned for account resources.
 *
 * @param username the account username
 * @param role the account role code
 * @param status the account status
 * @param baseData audit metadata for the account
 */
@Builder
public record AccountResponse(
                String username,

                Roles role,

                AccountStatus status,

                BaseData baseData) {
}
