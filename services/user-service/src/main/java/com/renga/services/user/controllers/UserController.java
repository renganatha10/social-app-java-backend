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
    public User getUserById() {
        return userService.user(UUID.fromString("000d5d57-4712-46a4-bbf7-3d9baffb0472"));
    }

    @GetMapping("/followers")
    public List<User> getMyFollowers() {
        return userService.getMyFollowers(UUID.fromString("000d5d57-4712-46a4-bbf7-3d9baffb0472"));
    }

    @GetMapping("/followings")
    public List<User> getMyFollowings() {
        return userService.getMyFollowees(UUID.fromString("000d5d57-4712-46a4-bbf7-3d9baffb0472"));
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam(defaultValue = "") String searchText, Pageable pageable) {
        return userService.search(searchText, pageable);
    }

    @GetMapping("/followFollowingCount")
    public FollowFollowingCount getFollowerAndFollowingCount(){
        return userService.getFollowerAndFollowingCount(UUID.fromString("000d5d57-4712-46a4-bbf7-3d9baffb0472"));
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