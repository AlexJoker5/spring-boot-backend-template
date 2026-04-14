package com.main.java.features.account.dto.response;

import com.main.java.mapper.BaseData;
import lombok.Builder;
import lombok.Data;

@Builder
public record AccountResponse (
        String username,

        String password,

        String role,

        String status,

        BaseData baseData
){
}
