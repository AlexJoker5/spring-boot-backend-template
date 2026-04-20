package com.larvae.backend_template.features.userInfo.repository;

import com.larvae.backend_template.entity.UserInfo;
import com.larvae.backend_template.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface UserInfoRepository extends BaseRepository<UserInfo> {

    @EntityGraph(attributePaths = {"auth"})
    @Override
    Page<UserInfo> findAllByIsActiveTrue(Pageable pageable);

    @EntityGraph(attributePaths = {"auth"})
    @Override
    List<UserInfo> findAllByIsActiveTrue();

}
