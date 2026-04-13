package com.main.java.features.userInfo.mapper;

import com.main.java.entity.UserInfo;
import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.dto.response.UserInfoResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-13T11:38:42+0630",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UserInfoMapperImpl implements UserInfoMapper {

    @Override
    public UserInfo toEntity(UserInfoRequest req) {
        if ( req == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setJoinDate( req.getJoinDate() );
        userInfo.setResignDate( req.getResignDate() );
        userInfo.setStatus( req.getStatus() );

        return userInfo;
    }

    @Override
    public UserInfoResponse toResponseDto(UserInfo entity) {
        if ( entity == null ) {
            return null;
        }

        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setJoinDate( entity.getJoinDate() );
        userInfoResponse.setResignDate( entity.getResignDate() );
        userInfoResponse.setStatus( entity.getStatus() );

        return userInfoResponse;
    }

    @Override
    public void updateEntityFromRequestDto(UserInfoRequest request, UserInfo entity) {
        if ( request == null ) {
            return;
        }

        entity.setJoinDate( request.getJoinDate() );
        entity.setResignDate( request.getResignDate() );
        entity.setStatus( request.getStatus() );
    }
}
