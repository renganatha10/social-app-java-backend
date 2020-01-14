package com.renga.services.user.repositories;

import java.util.UUID;

import com.renga.services.user.models.UserFollowingEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserFollowingRepository extends JpaRepository<UserFollowingEntity, UUID>, JpaSpecificationExecutor<UserFollowingEntity>  {
}