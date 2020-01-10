package com.renga.services.post.repositories;

import java.util.List;
import java.util.UUID;

import com.renga.services.post.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends JpaRepository<Post, UUID>, PagingAndSortingRepository<Post, UUID> {
    List<Post> findByUserId(UUID userId);
}