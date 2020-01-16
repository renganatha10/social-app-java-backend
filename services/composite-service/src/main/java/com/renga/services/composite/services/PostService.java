package com.renga.services.composite.services;


import com.renga.api.models.Comment;
import com.renga.api.models.CommentLikeCount;
import com.renga.api.models.Post;
import com.renga.api.models.PostLike;
import com.renga.services.composite.lookups.PostDetail;
import com.renga.services.composite.mappers.PostDetailMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final RestTemplate restTemplate;
    private final PostDetailMapper postDetailMapper;
    private final String postServiceUrl;
    private final HttpEntity<String> getEntity;
    private final HttpHeaders headers;

    @Autowired
    public PostService(
            final RestTemplate restTemplate,
            final PostDetailMapper postDetailMapper,
            @Value("${services.post}")
            final String postServiceUrl) {
        this.restTemplate = restTemplate;
        this.postDetailMapper = postDetailMapper;
        this.postServiceUrl = postServiceUrl;

        this.headers = new HttpHeaders();
        this.headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        this.getEntity = new HttpEntity<>(this.headers);
    }


    private ResponseEntity<CommentLikeCount> getCommentAndLikeCount(String postId) {
        String commentLikeCountUrl = this.postServiceUrl + "commentLikeCount/" + postId;
        return restTemplate.exchange(commentLikeCountUrl, HttpMethod.GET, getEntity, CommentLikeCount.class);
    }


    public List<PostDetail> getAllMyPost()  {
        List<PostDetail> postDetails = new ArrayList<>();
        ResponseEntity<Post[]> postResponse = restTemplate.exchange(this.postServiceUrl + "posts", HttpMethod.GET, getEntity, Post[].class);
        List<Post> posts = Arrays.asList(postResponse.getBody());
        List<ResponseEntity<CommentLikeCount>> commentLikeCountResponses =
                posts.stream()
                        .map(post -> getCommentAndLikeCount(post.getId().toString()))
                        .collect(Collectors.toList());
        commentLikeCountResponses.stream().forEach(counts -> {
            CommentLikeCount clCount = counts.getBody();
            PostDetail postDetail = postDetailMapper.toPostDetailFromPost
                    (posts.stream()
                            .filter(post -> post.getId().equals(clCount.getPostId())).findFirst().orElse(new Post()));
            postDetail.setCommentCount((long) clCount.getCommentCount());
            postDetail.setLikeCount((long) clCount.getCommentCount());
            postDetails.add(postDetail);
        });
        return postDetails;
    }

    public List<Comment> getAllMyComments(final UUID postId) {
        String getCommentUrl = this.postServiceUrl + "comment/" + postId;
        ResponseEntity<Comment[]> postResponse = restTemplate.exchange(getCommentUrl, HttpMethod.GET, getEntity, Comment[].class);
        return Arrays.asList(postResponse.getBody());
    }

    public void createPost(final Post post) {
        HttpEntity<Post> entity = new HttpEntity<Post>(post,this.headers);
        restTemplate.exchange(this.postServiceUrl, HttpMethod.POST, entity, String.class);
    }

    public void createComment(final Comment comment) {
        String createCommentUrl = this.postServiceUrl + "comment";
        HttpEntity<Comment> entity = new HttpEntity<>(comment,this.headers);
        restTemplate.exchange(createCommentUrl, HttpMethod.POST, entity, String.class);
    }

    public void like(final PostLike postLike) {
        String createCommentUrl = this.postServiceUrl + "like";
        HttpEntity<PostLike> entity = new HttpEntity<>(postLike,this.headers);
        restTemplate.exchange(createCommentUrl, HttpMethod.POST, entity, String.class);
    }

    public void unlike(final PostLike postLike) {
        String createCommentUrl = this.postServiceUrl + "unlike";
        HttpEntity<PostLike> entity = new HttpEntity<>(postLike,this.headers);
        restTemplate.exchange(createCommentUrl, HttpMethod.POST, entity, String.class);
    }
}