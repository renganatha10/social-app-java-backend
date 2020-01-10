package com.renga.services.post.repositories;

import java.util.List;
import java.util.UUID;

import com.renga.services.post.models.Post;
import com.renga.services.post.models.PostLike;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, UUID> {
    List<PostLike> findByPost(Post post);
}