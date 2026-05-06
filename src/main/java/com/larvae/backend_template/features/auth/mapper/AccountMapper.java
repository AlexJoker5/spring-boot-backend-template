package com.larvae.backend_template.features.auth.mapper;

import com.larvae.backend_template.entity.Account;
import com.larvae.backend_template.features.auth.dto.request.AccountRequest;
import com.larvae.backend_template.features.auth.dto.response.AccountResponse;
import com.larvae.backend_template.mapper.BaseDataMapper;
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
        if (entity == null)
            return null;
        return AccountResponse.builder()
                .username(entity.getUsername())
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
        entity.setStatus(request.status());
        entity.setRole(request.role());
    }

}
