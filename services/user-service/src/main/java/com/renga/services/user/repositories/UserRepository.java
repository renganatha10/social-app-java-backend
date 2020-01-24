package com.renga.services.user.repositories;

import java.util.UUID;

import com.google.common.base.Optional;
import com.renga.services.user.models.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity>, PagingAndSortingRepository<UserEntity, UUID>  {
    Optional<UserEntity> findByEmail(String email);
}