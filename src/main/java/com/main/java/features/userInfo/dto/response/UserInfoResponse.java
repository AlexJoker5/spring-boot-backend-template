package com.main.java.features.userInfo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserInfoResponse {

    private String firstName;

    private String lastName;

    private LocalDateTime joinDate;

    private LocalDateTime resignDate;

    private UUID accountId;

}
