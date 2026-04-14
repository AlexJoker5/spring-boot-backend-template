package com.main.java.features.account.dto.response;

import com.main.java.mapper.BaseData;

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

                String role,

                String status,

                BaseData baseData) {
}
