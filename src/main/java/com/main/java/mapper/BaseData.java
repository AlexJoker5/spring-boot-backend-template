package com.main.java.mapper;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record BaseData (
        UUID id,
        Boolean isActive,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
){
}
