package com.main.java.mapper;

import com.main.java.entity.BaseEntity;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface BaseMapper<ENTITY extends BaseEntity, REQUEST, RESPONSE> {

    @Mapping(target = "id", ignore = true)
    ENTITY toEntity(REQUEST req);

    RESPONSE toResponseDto(ENTITY entity);

    void updateEntityFromRequestDto(REQUEST request, @MappingTarget ENTITY entity);

}
