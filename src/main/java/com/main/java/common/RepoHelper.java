package com.main.java.common;

import com.main.java.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public class RepoHelper {

    public static <ENTITY, UUID> ENTITY findByIdOrThrow(JpaRepository<ENTITY, UUID> repository,
                                                        UUID id, String entityName, String idFieldName) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        entityName + "not found with " + idFieldName + ": " + id
                ));
    }

}
