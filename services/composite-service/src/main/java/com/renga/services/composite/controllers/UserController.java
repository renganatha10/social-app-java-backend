package com.renga.services.composite.controllers;

import com.renga.api.models.DefaultResponse;
import com.renga.api.models.User;
import com.renga.api.models.FollowUnFollowBody;
import com.renga.services.composite.lookups.UserDetail;
import com.renga.services.composite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
     @Autowired
     private UserService userService;

    @GetMapping("/")
    public UserDetail getUserById(Authentication authentication) {
        return userService.getUserById(UUID.fromString(authentication.getPrincipal().toString()));
    }

    @GetMapping("/followers")
    public List<User> getMyFollowers(Authentication authentication) {
        return userService.getMyFollowers(UUID.fromString(authentication.getPrincipal().toString()));
    }

    @GetMapping("/followings")
    public List<User> getMyFollowings(Authentication authentication) {
        return userService.getMyFollowings(UUID.fromString(authentication.getPrincipal().toString()));
    }

    @GetMapping("/search")
    public List<User> search(
            @RequestParam(defaultValue = "") String searchText,
            @RequestParam(defaultValue = "firstName") String sort,
            @RequestParam(defaultValue = "up") String direction
    ) {
        return userService.search(searchText, sort, direction);
    }


    @PostMapping("/")
    public ResponseEntity<DefaultResponse> createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>(new DefaultResponse("User Created"), HttpStatus.CREATED);
    }

    @PostMapping("/follow")
    public ResponseEntity<DefaultResponse> follow(@Valid @RequestBody FollowUnFollowBody followBody) {
        userService.follow(UUID.fromString(followBody.getFollowerId()), UUID.fromString(followBody.getUserId()));
        return new ResponseEntity<>(new DefaultResponse("User Followed"), HttpStatus.CREATED);
    }

    @PostMapping("/unFollow")
    public ResponseEntity<DefaultResponse> unFollow(@Valid @RequestBody FollowUnFollowBody unfollowBody) {
        userService.unfollow(UUID.fromString(unfollowBody.getFollowerId()), UUID.fromString(unfollowBody.getUserId()));
        return new ResponseEntity<>(new DefaultResponse("User UnFollowed"), HttpStatus.CREATED);
    }

}
