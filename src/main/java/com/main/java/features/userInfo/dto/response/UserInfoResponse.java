package com.main.java.features.userInfo.dto.response;

import com.main.java.mapper.BaseData;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

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
