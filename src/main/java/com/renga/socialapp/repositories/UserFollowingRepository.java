package com.renga.socialapp.repositories;

import java.util.UUID;

import com.renga.socialapp.models.UserFollowing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserFollowingRepository extends JpaRepository<UserFollowing, UUID>, JpaSpecificationExecutor<UserFollowing>  {
}