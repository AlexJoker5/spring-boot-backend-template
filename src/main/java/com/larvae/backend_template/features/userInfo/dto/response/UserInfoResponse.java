package com.larvae.backend_template.features.userInfo.dto.response;

import com.larvae.backend_template.mapper.BaseData;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Response payload returned for user information resources.
 *
 * @param firstName the user's first name
 * @param lastName the user's last name
 * @param joinDate the user's join date
 * @param resignDate the user's resignation date
 * @param accountId the associated auth UUID
 * @param baseData audit metadata for the record
 */
@Builder
public record UserInfoResponse (
        String firstName,

        String lastName,

        LocalDateTime joinDate,

        LocalDateTime resignDate,

        UUID accountId,

        BaseData baseData
){
}
