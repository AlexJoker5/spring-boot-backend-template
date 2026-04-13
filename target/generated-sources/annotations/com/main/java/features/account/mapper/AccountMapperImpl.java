package com.main.java.features.account.mapper;

import com.main.java.entity.Account;
import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.dto.response.AccountResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-13T20:50:47+0630",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(AccountRequest arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Account account = new Account();

        account.setUsername( arg0.getUsername() );
        account.setPassword( arg0.getPassword() );
        account.setRole( arg0.getRole() );
        account.setStatus( arg0.getStatus() );

        return account;
    }

    @Override
    public AccountResponse toResponseDto(Account entity) {
        if ( entity == null ) {
            return null;
        }

        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setUsername( entity.getUsername() );
        accountResponse.setPassword( entity.getPassword() );
        accountResponse.setRole( entity.getRole() );
        accountResponse.setStatus( entity.getStatus() );

        return accountResponse;
    }

    @Override
    public void updateEntityFromRequestDto(AccountRequest arg0, Account arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setUsername( arg0.getUsername() );
        arg1.setPassword( arg0.getPassword() );
        arg1.setRole( arg0.getRole() );
        arg1.setStatus( arg0.getStatus() );
    }
}
