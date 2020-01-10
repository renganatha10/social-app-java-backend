package com.renga.services.post.services;

import java.util.List;
import java.util.UUID;

import com.renga.services.post.lookups.CommentLikeCount;
import com.renga.services.post.lookups.PostCount;
import com.renga.services.post.models.Comment;
import com.renga.services.post.models.Post;
import com.renga.services.post.models.PostLike;
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

    public List<Post> getAllMyPost() {
        return postRepository.findAll();
    }

    public List<Comment> getAllMyComments(UUID postId) {
        return commentRepository.findByPost(new Post(postId));
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public PostLike like(PostLike postLike) {
        return postLikeRepository.save(postLike);
    }

    public void unlike(PostLike postLike) {
        postLikeRepository.delete(postLike);
    }

    public PostCount getMyPostCount(UUID userId) {
        return new PostCount(postRepository.findByUserId(userId).size());
    }


    public CommentLikeCount getMyPostCommentLikeComment(UUID postId) {
        int postLikeCount = postLikeRepository.findByPost(new Post(postId)).size();
        int commentCount = commentRepository.findByPost(new Post(postId)).size();
        return new CommentLikeCount(postLikeCount, commentCount);
    }

}