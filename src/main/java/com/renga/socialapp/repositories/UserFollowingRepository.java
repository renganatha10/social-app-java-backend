package com.renga.socialapp.repositories;

import java.util.List;
import java.util.UUID;

import com.renga.socialapp.models.User;
import com.renga.socialapp.models.UserFollowing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowingRepository extends JpaRepository<UserFollowing, UUID>{
    List<UserFollowing> findByFollowee(User follower);
}