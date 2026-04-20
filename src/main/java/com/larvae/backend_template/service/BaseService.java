package com.larvae.backend_template.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

/**
 * Base service interface defining common CRUD operations used by feature services.
 *
 * @param <REQUEST> request DTO type
 * @param <RESPONSE> response DTO type
 */
public interface BaseService<REQUEST, RESPONSE> {
    /**
     * Create a new resource from the provided request.
     *
     * @param request the DTO containing creation data
     * @return created response DTO
     */
    RESPONSE create(REQUEST request);

    /**
     * Find a single resource by its UUID.
     *
     * @param id the resource identifier
     * @return response DTO for the resource
     */
    RESPONSE findById(UUID id);

    /**
     * Retrieve all resources.
     *
     * @return list of response DTOs
     */
    List<RESPONSE> findAll();

    /**
     * Find resources by a list of UUIDs.
     *
     * @param ids list of resource identifiers
     * @return list of response DTOs
     */
    List<RESPONSE> findByIds(List<UUID> ids);

    /**
     * Update an existing resource identified by UUID.
     *
     * @param id the resource identifier
     * @param request the DTO containing update data
     * @return updated response DTO
     */
    RESPONSE update(UUID id, REQUEST request);

    /**
     * Soft delete a single resource by UUID.
     *
     * @param id the resource identifier
     */
    void delete(UUID id);

    /**
     * Soft delete multiple resources by UUID list.
     *
     * @param ids list of resource identifiers
     */
    void deleteByMany(List<UUID> ids);

    /**
     * Retrieve a paged list of resources.
     *
     * @param page page index
     * @param size page size
     * @return paged response DTOs
     */
    Page<RESPONSE> getAll(int page, int size);

    /**
     * Delete a single resource by UUID.
     *
     * @param id the resource identifier
     */
    void deleteByAdmin(UUID id);

    /**
     * Delete multiple resources by UUID list.
     *
     * @param ids list of resource identifiers
     */
    void deleteManyByAdmin(List<UUID> ids);
}
