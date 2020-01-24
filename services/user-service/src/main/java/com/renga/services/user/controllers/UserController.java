package com.renga.services.user.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.renga.api.models.FollowFollowingCount;
import com.renga.api.models.User;
import com.renga.api.models.DefaultResponse;
import com.renga.api.models.FollowUnFollowBody;
import com.renga.services.user.models.UserEntity;
import com.renga.services.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public User getUser(@RequestParam(defaultValue = "") String email) {
        return userService.user(email);
    }

    @GetMapping("/followers/:userId")
    public List<User> getMyFollowers(@RequestParam String userId) {
        return userService.getMyFollowers(UUID.fromString(userId));
    }

    @GetMapping("/followings/")
    public List<User> getMyFollowings(@RequestParam String userId) {
        return userService.getMyFollowees(UUID.fromString(userId));
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam(defaultValue = "") String searchText, Pageable pageable) {
        return userService.search(searchText, pageable);
    }

    @GetMapping("/followFollowingCount/:userId")
    public FollowFollowingCount getFollowerAndFollowingCount(@RequestParam String userId){
        return userService.getFollowerAndFollowingCount(UUID.fromString(userId));
    }

    @PostMapping("/")
    public ResponseEntity<DefaultResponse> createUser(@Valid @RequestBody UserEntity user) {
        userService.createUser(user);
        return new ResponseEntity<>(new DefaultResponse( "User Created"), HttpStatus.CREATED);
    }

    @PostMapping("/follow")
    public ResponseEntity<DefaultResponse> follow(@Valid @RequestBody FollowUnFollowBody followBody) {
        userService.follow(UUID.fromString(followBody.getFollowerId()), UUID.fromString(followBody.getUserId()));
        return new ResponseEntity<>(new DefaultResponse( "User Followed"), HttpStatus.CREATED);
    }

    @PostMapping("/unFollow")
    public ResponseEntity<DefaultResponse> unFollow(@Valid @RequestBody FollowUnFollowBody unfollowBody) {
        userService.unfollow(UUID.fromString(unfollowBody.getFollowerId()), UUID.fromString(unfollowBody.getUserId()));
        return new ResponseEntity<>(new DefaultResponse("User UnFollowed"),HttpStatus.CREATED);
    }

}