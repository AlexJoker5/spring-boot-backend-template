package com.main.java.service.impl;

import com.main.java.entity.BaseEntity;
import com.main.java.repository.BaseRepository;
import com.main.java.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return mapEntitytoResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public RESPONSE findById(UUID id) {
        ENTITY entity = findByIdOrThrow(id, repository);
        return mapEntitytoResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RESPONSE> findAll() {
        List<ENTITY> entityList = repository.findAll();
        return entityList.stream().map(this::mapEntitytoResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RESPONSE> findByIds(List<UUID> ids) {
        List<ENTITY> entityList = repository.findAllById(ids);
        return entityList.stream().map(this::mapEntitytoResponse).toList();
    }

    @Override
    @Transactional
    public RESPONSE update(UUID id, REQUEST request) {
        ENTITY entity = findByIdOrThrow(id, repository);
        updateEntityFromRequest(entity, request);
        ENTITY updatedEntity = repository.save(entity);
        return mapEntitytoResponse(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByMany(List<UUID> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RESPONSE> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ENTITY> entityPages = repository.findAll(pageable);
        return entityPages.map(this::mapEntitytoResponse);
    }

    protected ENTITY findByIdOrThrow(UUID id, BaseRepository<ENTITY> repo) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    protected abstract ENTITY mapRequestToEntity(REQUEST request);

    protected abstract RESPONSE mapEntitytoResponse(ENTITY entity);

    protected abstract void updateEntityFromRequest(ENTITY entity, REQUEST request);

}
