package com.main.java.features.userInfo.mapper;

import com.main.java.entity.Account;
import com.main.java.entity.UserInfo;
import com.main.java.features.account.mapper.AccountMapper;
import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.dto.response.UserInfoResponse;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-13T17:14:27+0630",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserInfoMapperImpl implements UserInfoMapper {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void updateEntityFromRequestDto(UserInfoRequest request, UserInfo entity) {
        if ( request == null ) {
            return;
        }

        entity.setFirstName( request.getFirstName() );
        entity.setLastName( request.getLastName() );
        entity.setJoinDate( request.getJoinDate() );
        entity.setResignDate( request.getResignDate() );
        entity.setAccountId( accountMapper.fromId( request.getAccountId() ) );
    }

    @Override
    public UserInfoResponse toResponseDto(UserInfo entity) {
        if ( entity == null ) {
            return null;
        }

        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setAccountId( entityAccountIdId( entity ) );
        userInfoResponse.setFirstName( entity.getFirstName() );
        userInfoResponse.setLastName( entity.getLastName() );
        userInfoResponse.setJoinDate( entity.getJoinDate() );
        userInfoResponse.setResignDate( entity.getResignDate() );

        return userInfoResponse;
    }

    @Override
    public UserInfo toEntity(UserInfoRequest request) {
        if ( request == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setAccountId( uuidToAccount( request.getAccountId() ) );
        userInfo.setFirstName( request.getFirstName() );
        userInfo.setLastName( request.getLastName() );
        userInfo.setJoinDate( request.getJoinDate() );
        userInfo.setResignDate( request.getResignDate() );

        return userInfo;
    }

    private UUID entityAccountIdId(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }
        Account accountId = userInfo.getAccountId();
        if ( accountId == null ) {
            return null;
        }
        UUID id = accountId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
