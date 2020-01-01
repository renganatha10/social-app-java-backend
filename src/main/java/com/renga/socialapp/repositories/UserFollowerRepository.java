package com.renga.socialapp.repositories;

import java.util.UUID;

import com.renga.socialapp.models.UserFollower;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserFollowerRepository extends JpaRepository<UserFollower, UUID>, JpaSpecificationExecutor<UserFollower>{
}