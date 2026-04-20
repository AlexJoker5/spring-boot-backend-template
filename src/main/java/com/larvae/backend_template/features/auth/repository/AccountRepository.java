package com.larvae.backend_template.features.auth.repository;

import com.larvae.backend_template.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import com.larvae.backend_template.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<Account> {

    Optional<Account> findByUsernameAndIsActiveTrue(String username);

}
