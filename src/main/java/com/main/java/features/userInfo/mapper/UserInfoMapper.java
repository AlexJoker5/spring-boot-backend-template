package com.main.java.features.userInfo.mapper;

import com.main.java.entity.UserInfo;
import com.main.java.features.userInfo.dto.request.UserInfoRequest;
import com.main.java.features.userInfo.dto.response.UserInfoResponse;
import com.main.java.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInfoMapper extends BaseMapper<UserInfo, UserInfoRequest, UserInfoResponse> {
}
