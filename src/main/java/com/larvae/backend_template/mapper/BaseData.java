package com.larvae.backend_template.mapper;

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
