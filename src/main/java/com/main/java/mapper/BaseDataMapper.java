package com.main.java.mapper;

import com.main.java.entity.BaseEntity;
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
