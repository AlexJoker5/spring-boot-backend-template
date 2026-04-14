package com.main.java.features.userInfo.mapper;

import com.main.java.entity.Account;
import com.main.java.entity.UserInfo;
import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.dto.response.UserInfoResponse;
import com.main.java.mapper.BaseDataMapper;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {

    private final BaseDataMapper baseDataMapper;

    public UserInfoMapper(BaseDataMapper baseDataMapper) {
        this.baseDataMapper = baseDataMapper;
    }

    public UserInfo toEntity(UserInfoRequest request, Account account) {
        UserInfo entity = new UserInfo();
        setRequestToEntity(entity, request, account);
        return entity;
    }

    public UserInfoResponse toResponse(UserInfo entity) {
        if(entity == null) return null;
        return UserInfoResponse.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .joinDate(entity.getJoinDate())
                .resignDate(entity.getResignDate())
                .accountId(entity.getAccount().getId())
                .baseData(baseDataMapper.toBasicData(entity))
                .build();
    }

    public void updateEntity(UserInfo entity, UserInfoRequest request, Account account) {
        setRequestToEntity(entity, request, account);
    }

    private void setRequestToEntity(UserInfo entity, UserInfoRequest request, Account account) {
        entity.setFirstName(request.firstName());
        entity.setLastName(request.lastName());
        entity.setJoinDate(request.joinDate());
        entity.setResignDate(request.resignDate());
        entity.setAccount(account);
    }

}
