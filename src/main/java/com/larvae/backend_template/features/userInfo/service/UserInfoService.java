package com.larvae.backend_template.features.userInfo.service;

import com.larvae.backend_template.features.userInfo.dto.request.UserInfoRequest;
import com.larvae.backend_template.features.userInfo.dto.response.UserInfoResponse;
import com.larvae.backend_template.service.BaseService;

/**
 * Service interface for user information business operations.
 */
public interface UserInfoService extends BaseService<UserInfoRequest, UserInfoResponse> {
}
