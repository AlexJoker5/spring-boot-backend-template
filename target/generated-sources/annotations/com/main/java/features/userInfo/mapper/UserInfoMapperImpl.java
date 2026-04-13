package com.main.java.features.userInfo.mapper;

import com.main.java.entity.UserInfo;
import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.dto.response.UserInfoResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-13T11:10:09+0630",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UserInfoMapperImpl implements UserInfoMapper {

    @Override
    public UserInfo toEntity(UserInfoRequest arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setStatus( arg0.getStatus() );
        userInfo.setJoinDate( arg0.getJoinDate() );
        userInfo.setResignDate( arg0.getResignDate() );

        return userInfo;
    }

    @Override
    public UserInfoResponse toResponseDto(UserInfo entity) {
        if ( entity == null ) {
            return null;
        }

        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setStatus( entity.getStatus() );
        userInfoResponse.setJoinDate( entity.getJoinDate() );
        userInfoResponse.setResignDate( entity.getResignDate() );

        return userInfoResponse;
    }

    @Override
    public void updateEntityFromRequestDto(UserInfoRequest arg0, UserInfo arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setStatus( arg0.getStatus() );
        arg1.setJoinDate( arg0.getJoinDate() );
        arg1.setResignDate( arg0.getResignDate() );
    }
}
