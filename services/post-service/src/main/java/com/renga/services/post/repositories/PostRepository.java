package com.renga.services.post.repositories;

import java.util.List;
import java.util.UUID;

import com.renga.services.post.models.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends JpaRepository<PostEntity, UUID>, PagingAndSortingRepository<PostEntity, UUID> {
    List<PostEntity> findByUserId(UUID userId);
}