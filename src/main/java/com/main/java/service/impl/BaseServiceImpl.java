package com.main.java.service.impl;

import com.main.java.entity.BaseEntity;
import com.main.java.mapper.BaseMapper;
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
    protected final BaseMapper<ENTITY, REQUEST, RESPONSE> mapper;

    protected BaseServiceImpl(BaseRepository<ENTITY> repository,
                              BaseMapper<ENTITY, REQUEST, RESPONSE> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public RESPONSE create(REQUEST request) {
        ENTITY entity = mapper.toEntity(request);
        ENTITY savedEntity = repository.save(entity);
        return mapper.toResponseDto(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public RESPONSE findById(UUID id) {
        ENTITY entity = findByIdOrThrow(id);
        return mapper.toResponseDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RESPONSE> findAll() {
        List<ENTITY> entityList = repository.findAll();
        return entityList.stream().map(mapper::toResponseDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RESPONSE> findbyIds(List<UUID> ids) {
        List<ENTITY> entityList = repository.findAllById(ids);
        return entityList.stream().map(mapper::toResponseDto).toList();
    }

    @Override
    @Transactional
    public RESPONSE update(UUID id, REQUEST request) {
        ENTITY entity = findByIdOrThrow(id);
        mapper.updateEntityFromRequestDto(request, entity);
        ENTITY updatedEntity = repository.save(entity);
        return mapper.toResponseDto(updatedEntity);
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

    @Override
    @Transactional(readOnly = true)
    public Page<RESPONSE> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ENTITY> entityPages = repository.findAll(pageable);
        return entityPages.map(mapper::toResponseDto);
    }

    private ENTITY findByIdOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }
}
