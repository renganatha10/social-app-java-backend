package com.renga.services.post.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.renga.api.models.*;
import com.renga.services.post.models.CommentEntity;
import com.renga.services.post.models.PostEntity;
import com.renga.services.post.models.PostLikeEntity;
import com.renga.services.post.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts/{userId")
    public List<Post> getMyPosts(@PathVariable String userId) {
        return postService.getAllMyPost(UUID.fromString(userId));
    }

    @GetMapping("/count/{userId}")
    public PostCount getMyPostCounts(@PathVariable String userId) {
        return postService.getMyPostCount(UUID.fromString(userId));
    }

    @GetMapping("/commentLikeCount/{postId}")
    public CommentLikeCount getMyPostCommentLikeComment(@PathVariable String postId) {
        return postService.getMyPostCommentLikeComment(UUID.fromString(postId));
    }

    @GetMapping(path = "/comment/{postId}")
    public List<Comment> getAllMyComments(@PathVariable String postId) {
        return postService.getAllMyComments(UUID.fromString(postId));
    }

    @PostMapping("/")
    public ResponseEntity<DefaultResponse> createPost(@Valid PostEntity post) {
        postService.createPost(post);
        return new ResponseEntity<>(new DefaultResponse("Post Created"), HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<DefaultResponse> createComment(@Valid CommentEntity comment) {
        postService.createComment(comment);
        return new ResponseEntity<>(new DefaultResponse("Comment Created"), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<DefaultResponse> likePost(@Valid PostLikeEntity postLike) {
        postService.like(postLike);
        return new ResponseEntity<>(new DefaultResponse("Post liked"), HttpStatus.OK);
    }

    @PostMapping("/unlike")
    public ResponseEntity<DefaultResponse> unlikePost(@Valid PostLikeEntity postLike) {
        postService.unlike(postLike);
        return new ResponseEntity<>(new DefaultResponse("Post Unliked"), HttpStatus.OK);
    }
}
