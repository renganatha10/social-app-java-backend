package com.renga.services.post.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.renga.services.post.lookups.CommentLikeCount;
import com.renga.services.post.lookups.PostCount;
import com.renga.services.post.models.Comment;
import com.renga.services.post.models.Post;
import com.renga.services.post.models.PostLike;
import com.renga.services.post.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class PostController {
    @Autowired
    PostService postService;


    @GetMapping("/posts")
    public List<Post> getMyPosts() {
        return postService.getAllMyPost();
    }


    @GetMapping("/count")
    public PostCount getMyPostCounts() {
        return postService.getMyPostCount(UUID.fromString("000d5d57-4712-46a4-bbf7-3d9baffb0472"));
    }


    @GetMapping("/commentLikeCount/:postId")
    public CommentLikeCount getMyPostCommentLikeComment(@RequestParam UUID postId) {
        return postService.getMyPostCommentLikeComment(postId);
    }


    @GetMapping("/comment/:postId")
    public List<Comment> getAllMyComments(@RequestParam UUID postId) {
        return postService.getAllMyComments(postId);
    }

    @PostMapping("/")
    public Post createPost(@Valid Post post) {
        return postService.createPost(post);
    }


    @PostMapping("/comment")
    public Comment createComment(@Valid Comment comment) {
        return postService.createComment(comment);
    }

    @PostMapping("/like")
    public PostLike likePost(@Valid PostLike postLike) {
        return postService.like(postLike);
    }

    @PostMapping("/unlike")
    public PostLike unlikePost(@Valid PostLike postLike) {
        postService.unlike(postLike);
		return postLike;
    }
}