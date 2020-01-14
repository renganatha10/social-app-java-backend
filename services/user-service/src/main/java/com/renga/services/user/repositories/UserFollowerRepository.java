package com.renga.services.user.repositories;

import java.util.UUID;

import com.renga.services.user.models.UserFollowerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserFollowerRepository extends JpaRepository<UserFollowerEntity, UUID>, JpaSpecificationExecutor<UserFollowerEntity>{
}