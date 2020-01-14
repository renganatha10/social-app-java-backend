package com.renga.services.post.repositories;

import java.util.List;
import java.util.UUID;

import com.renga.services.post.models.PostEntity;
import com.renga.services.post.models.PostLikeEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, UUID> {
    List<PostLikeEntity> findByPost(PostEntity post);
}