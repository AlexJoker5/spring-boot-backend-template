package com.main.java.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface BaseService<REQUEST, RESPONSE> {
    RESPONSE create(REQUEST request);
    RESPONSE findById(UUID id);
    List<RESPONSE> findAll();
    List<RESPONSE> findbyIds(List<UUID> ids);
    RESPONSE update(UUID id, REQUEST request);
    void delete(UUID id);
    void deletebyMany(List<UUID> ids);
    Page<RESPONSE> getAll(int page, int size);
}
