package com.larvae.backend_template.service.impl;

import com.larvae.backend_template.entity.BaseEntity;
import com.larvae.backend_template.repository.BaseRepository;
import com.larvae.backend_template.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

public abstract class BaseServiceImpl<ENTITY extends BaseEntity, REQUEST, RESPONSE> implements BaseService<REQUEST, RESPONSE> {

    protected final BaseRepository<ENTITY> repository;

    protected BaseServiceImpl(BaseRepository<ENTITY> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public RESPONSE create(REQUEST request) {
        ENTITY entity = mapRequestToEntity(request);
        ENTITY savedEntity = repository.save(entity);
        return mapEntityToResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public RESPONSE findById(UUID id) {
        ENTITY entity = findByIdOrThrow(id, repository);
        return mapEntityToResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RESPONSE> findAll() {
        List<ENTITY> entityList = repository.findAllByIsActiveTrue();
        return entityList.stream().map(this::mapEntityToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RESPONSE> findByIds(List<UUID> ids) {
        List<ENTITY> entityList = repository.findAllByIdInAndIsActiveTrue(ids);
        return entityList.stream().map(this::mapEntityToResponse).toList();
    }

    @Override
    @Transactional
    public RESPONSE update(UUID id, REQUEST request) {
        ENTITY entity = findByIdOrThrow(id, repository);
        updateEntityFromRequest(entity, request);
        ENTITY updatedEntity = repository.save(entity);
        return mapEntityToResponse(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        ENTITY entity = findByIdOrThrow(id, repository);
        entity.setIsActive(false);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteByMany(List<UUID> ids) {
        List<ENTITY> entityList = repository.findAllById(ids);
        entityList.forEach(entity -> entity.setIsActive(false));
        repository.saveAll(entityList);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RESPONSE> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ENTITY> entityPages = repository.findAllByIsActiveTrue(pageable);
        return entityPages.map(this::mapEntityToResponse);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteByAdmin(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteManyByAdmin(List<UUID> ids) {
        repository.deleteAllById(ids);
    }

    protected <ENT extends BaseEntity> ENT findByIdOrThrow(UUID id, BaseRepository<ENT> repo) {
        return repo.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    protected abstract ENTITY mapRequestToEntity(REQUEST request);

    protected abstract RESPONSE mapEntityToResponse(ENTITY entity);

    protected abstract void updateEntityFromRequest(ENTITY entity, REQUEST request);



}
