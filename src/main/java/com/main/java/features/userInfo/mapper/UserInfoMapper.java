package com.main.java.features.userInfo.mapper;

import com.main.java.entity.Account;
import com.main.java.entity.UserInfo;
import com.main.java.features.account.mapper.AccountMapper;
import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.dto.response.UserInfoResponse;
import com.main.java.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface UserInfoMapper extends BaseMapper<UserInfo, UserInfoRequest, UserInfoResponse> {

    @Override
    @Mapping(target = "accountId", source = "accountId.id")
    UserInfoResponse toResponseDto(UserInfo entity);

    @Override
    @Mapping(target = "accountId", source = "accountId", qualifiedByName = "uuidToAccount")
    UserInfo toEntity(UserInfoRequest request);

    @Named("uuidToAccount")
    default Account uuidToAccount(UUID accountId) {
        if (accountId == null) return null;
        Account account = new Account();
        account.setId(accountId);
        return account;
    }

}
