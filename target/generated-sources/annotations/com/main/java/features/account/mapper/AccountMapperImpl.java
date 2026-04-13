package com.main.java.features.account.mapper;

import com.main.java.entity.Account;
import com.main.java.features.account.dto.request.AccountRequest;
import com.main.java.features.account.dto.response.AccountResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-13T17:14:27+0630",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(AccountRequest req) {
        if ( req == null ) {
            return null;
        }

        Account account = new Account();

        account.setUsername( req.getUsername() );
        account.setPassword( req.getPassword() );
        account.setRole( req.getRole() );
        account.setStatus( req.getStatus() );

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
    public void updateEntityFromRequestDto(AccountRequest request, Account entity) {
        if ( request == null ) {
            return;
        }

        entity.setUsername( request.getUsername() );
        entity.setPassword( request.getPassword() );
        entity.setRole( request.getRole() );
        entity.setStatus( request.getStatus() );
    }
}
