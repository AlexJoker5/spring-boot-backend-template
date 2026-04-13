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
    date = "2026-04-13T20:50:46+0630",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UserInfoMapperImpl implements UserInfoMapper {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void updateEntityFromRequestDto(UserInfoRequest arg0, UserInfo arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setFirstName( arg0.getFirstName() );
        arg1.setLastName( arg0.getLastName() );
        arg1.setJoinDate( arg0.getJoinDate() );
        arg1.setResignDate( arg0.getResignDate() );
        arg1.setAccountId( accountMapper.fromId( arg0.getAccountId() ) );
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
