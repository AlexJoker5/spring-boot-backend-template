package com.larvae.backend_template.features.address.repository;

import com.larvae.backend_template.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.larvae.backend_template.entity.Address;

@Repository
public interface AddressRepository extends BaseRepository<Address> {

    @EntityGraph(attributePaths = {"userInfo"})
    @Override
    Page<Address> findAllByIsActiveTrue(Pageable pageable);

}
