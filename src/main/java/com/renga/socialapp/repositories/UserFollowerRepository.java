package com.renga.socialapp.repositories;

import java.util.UUID;
import java.util.List;

import com.renga.socialapp.models.User;
import com.renga.socialapp.models.UserFollower;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserFollowerRepository extends JpaRepository<UserFollower, UUID>{
    List<UserFollower> findByFollower(User follower);
}