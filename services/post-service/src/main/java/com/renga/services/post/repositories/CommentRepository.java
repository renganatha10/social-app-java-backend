package com.renga.services.post.repositories;

import java.util.List;
import java.util.UUID;

import com.renga.services.post.models.CommentEntity;
import com.renga.services.post.models.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID>, PagingAndSortingRepository<CommentEntity, UUID> {
    List<CommentEntity> findByPost(PostEntity post);
}