package com.main.java.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.main.java.entity.Account;

@Repository
public interface AccountRepo extends BaseRepository<Account>{

    Account findByEmpId(UUID empId);

}
