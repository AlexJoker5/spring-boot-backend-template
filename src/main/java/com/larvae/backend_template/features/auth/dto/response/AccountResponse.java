package com.larvae.backend_template.features.auth.dto.response;

import com.larvae.backend_template.enums.AccountStatus;
import com.larvae.backend_template.enums.Roles;
import com.larvae.backend_template.mapper.BaseData;

import lombok.Builder;

/**
 * Response payload returned for auth resources.
 *
 * @param username the auth username
 * @param role the auth role code
 * @param status the auth status
 * @param baseData audit metadata for the auth
 */
@Builder
public record AccountResponse(
                String username,

                Roles role,

                AccountStatus status,

                BaseData baseData) {
}
