package com.renga.services.composite.controllers;

import com.renga.api.models.Comment;
import com.renga.api.models.DefaultResponse;
import com.renga.api.models.Post;
import com.renga.api.models.PostLike;
import com.renga.services.composite.lookups.PostDetail;
import com.renga.services.composite.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public List<PostDetail> getMyPosts() {
        return postService.getAllMyPost();
    }

    @GetMapping("/comment/:postId")
    public List<Comment> getAllMyComments(@RequestParam UUID postId) {
        return postService.getAllMyComments(postId);
    }

    @PostMapping("/")
    public ResponseEntity<DefaultResponse>  createPost(@Valid Post post) {
        postService.createPost(post);
        return new ResponseEntity<>(new DefaultResponse("Post Created"), HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<DefaultResponse>  createComment(@Valid Comment comment) {
        postService.createComment(comment);
        return new ResponseEntity<>(new DefaultResponse("Comment Created"), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<DefaultResponse> likePost(@Valid PostLike postLike) {
        postService.like(postLike);
        return new ResponseEntity<>(new DefaultResponse("Post liked"), HttpStatus.OK);
    }

    @PostMapping("/unlike")
    public ResponseEntity<DefaultResponse> unlikePost(@Valid PostLike postLike) {
        postService.unlike(postLike);
        return new ResponseEntity<>(new DefaultResponse("Post Unlike"), HttpStatus.OK);
    }

}