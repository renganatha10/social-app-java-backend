package com.renga.services.post.services;

import java.util.List;
import java.util.UUID;

import com.renga.api.models.*;
import com.renga.services.post.mappers.CommentMapper;
import com.renga.services.post.mappers.PostMapper;
import com.renga.services.post.models.CommentEntity;
import com.renga.services.post.models.PostEntity;
import com.renga.services.post.models.PostLikeEntity;
import com.renga.services.post.repositories.CommentRepository;
import com.renga.services.post.repositories.PostLikeRepository;
import com.renga.services.post.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostLikeRepository postLikeRepository;
    @Autowired
    PostMapper postMapper;
    @Autowired
    CommentMapper commentMapper;

    public List<Post> getAllMyPost() {
        return postMapper.entityListToApiList(postRepository.findByUserId(UUID.fromString("000d5d57-4712-46a4-bbf7-3d9baffb0472")));
    }

    public List<Comment> getAllMyComments(UUID postId) {
        return  commentMapper.entityListToApiList(commentRepository.findByPost(new PostEntity(postId)));
    }

    public void createPost(PostEntity post) {
         postMapper.entityToApi(postRepository.save(post));
    }

    public void createComment(CommentEntity comment) {
        commentMapper.entityToApi(commentRepository.save(comment));
    }

    public void like(PostLikeEntity postLike) {
        postLikeRepository.save(postLike);
    }

    public void unlike(PostLikeEntity postLike) {
        postLikeRepository.delete(postLike);
    }

    public PostCount getMyPostCount(UUID userId) {
        return new PostCount(postRepository.findByUserId(userId).size());
    }

    public CommentLikeCount getMyPostCommentLikeComment(UUID postId) {
        int postLikeCount = postLikeRepository.findByPost(new PostEntity(postId)).size();
        int commentCount = commentRepository.findByPost(new PostEntity(postId)).size();
        return new CommentLikeCount(postLikeCount, commentCount, postId);
    }

}