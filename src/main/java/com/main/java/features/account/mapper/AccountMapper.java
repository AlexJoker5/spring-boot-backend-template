package com.main.java.features.account.mapper;

import com.main.java.entity.Account;
import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.dto.response.AccountResponse;
import com.main.java.mapper.BaseDataMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private final BaseDataMapper baseDataMapper;

    public AccountMapper(BaseDataMapper baseDataMapper) {
        this.baseDataMapper = baseDataMapper;
    }

    public Account toEntity(AccountRequest request) {
        Account entity = new Account();
        setRequestToEntity(entity, request);
        return entity;
    }

    public AccountResponse toResponse(Account entity) {
        if(entity == null) return null;
        return AccountResponse.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .role(entity.getRole())
                .baseData(baseDataMapper.toBasicData(entity))
                .build();
    }

    public void updateEntity(Account entity, AccountRequest request) {
        setRequestToEntity(entity, request);
    }

    private void setRequestToEntity(Account entity, AccountRequest request) {
        entity.setUsername(request.username());
        entity.setPassword(request.password());
        entity.setStatus(request.status());
        entity.setRole(request.role());
    }

}
