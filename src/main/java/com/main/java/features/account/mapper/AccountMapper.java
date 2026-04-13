package com.main.java.features.account.mapper;

import com.main.java.entity.Account;
import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.dto.response.AccountResponse;
import com.main.java.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AccountMapper extends BaseMapper<Account, AccountRequest, AccountResponse> {

    default Account fromId(UUID id) {
        if (id == null) return null;
        Account account = new Account();
        account.setId(id);
        return account;
    }

}
