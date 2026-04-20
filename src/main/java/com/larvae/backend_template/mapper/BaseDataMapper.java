package com.larvae.backend_template.mapper;

import com.larvae.backend_template.entity.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class BaseDataMapper {

    public BaseData toBasicData(BaseEntity entity){
        if(entity == null) return null;

        return BaseData.builder()
                .id(entity.getId())
                .isActive(entity.getIsActive())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }

}
