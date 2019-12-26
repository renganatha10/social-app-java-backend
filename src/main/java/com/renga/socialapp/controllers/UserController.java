package com.renga.socialapp.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.renga.socialapp.lookups.CreatedResponse;
import com.renga.socialapp.models.User;
import com.renga.socialapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public User getUserById() {
        return userService.user(UUID.fromString("00d5d57-4712-46a4-bbf7-3d9baffb0472"));
    }

    @GetMapping("/followers")
    public List<User> getMyFollowers() {
        return userService.getMyFollowers(UUID.fromString("00d5d57-4712-46a4-bbf7-3d9baffb0472"));
    }

    @GetMapping("/followings")
    public List<User> getMyFollowings() {
        return userService.getMyFollowees(UUID.fromString("00d5d57-4712-46a4-bbf7-3d9baffb0472"));
    }

    @GetMapping("/search")
    public List<User> search() {
        return userService.getMyFollowees(UUID.fromString("00d5d57-4712-46a4-bbf7-3d9baffb0472"));
    }

    @PostMapping("/")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/follow")
    public ResponseEntity<CreatedResponse> follow(@Valid @RequestBody FollowUnFollowBody followBody) {
        userService.follow(UUID.fromString(followBody.getFollowerId()), UUID.fromString(followBody.getUserId()));
        return new ResponseEntity<CreatedResponse>(new CreatedResponse( "User Followed"), HttpStatus.CREATED);
    }

    @PostMapping("/unFollow")
    public ResponseEntity<CreatedResponse> unFollow(@Valid @RequestBody FollowUnFollowBody unfollowBody) {
        userService.unfollow(UUID.fromString(unfollowBody.getFollowerId()), UUID.fromString(unfollowBody.getUserId()));
        return new ResponseEntity<CreatedResponse>(new CreatedResponse("User UnFollowed"),HttpStatus.CREATED);
    }

}