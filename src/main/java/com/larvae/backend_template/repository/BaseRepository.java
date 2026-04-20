package com.larvae.backend_template.repository;

import com.larvae.backend_template.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<ENTITY extends BaseEntity> extends JpaRepository<ENTITY, UUID> {

    Optional<ENTITY> findByIdAndIsActiveTrue(UUID id);

    Page<ENTITY> findAllByIsActiveTrue(Pageable pageable);

    List<ENTITY> findAllByIsActiveTrue();

    List<ENTITY> findAllByIdInAndIsActiveTrue(List<UUID> ids);

}
