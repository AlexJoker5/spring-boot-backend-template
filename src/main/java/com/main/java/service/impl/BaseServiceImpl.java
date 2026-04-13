package com.main.java.service.impl;

import com.main.java.entity.BaseEntity;
import com.main.java.repository.BaseRepository;
import com.main.java.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
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
        ENTITY entity = findByIdOrThrow(id);
        return mapEntityToResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RESPONSE> findAll() {
        List<ENTITY> entityList = repository.findAll();
        return entityList.stream().map(this::mapEntityToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RESPONSE> findbyIds(List<UUID> ids) {
        List<ENTITY> entityList = repository.findAllById(ids);
        return entityList.stream().map(this::mapEntityToResponse).toList();
    }

    @Override
    @Transactional
    public RESPONSE update(UUID id, REQUEST request) {
        ENTITY entity = findByIdOrThrow(id);
        updateEntityFromRequest(request, entity);
        ENTITY updatedEntity = repository.save(entity);
        return mapEntityToResponse(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deletebyMany(List<UUID> ids) {
        repository.deleteAllById(ids);
    }

    protected abstract ENTITY mapRequestToEntity(REQUEST request);

    protected abstract RESPONSE mapEntityToResponse(ENTITY entity);

    protected abstract void updateEntityFromRequest(REQUEST request, ENTITY entity);

    private ENTITY findByIdOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }
}
