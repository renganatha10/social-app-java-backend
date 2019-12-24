package com.renga.socialapp.controller;

import java.util.List;

import com.renga.socialapp.model.User;
import com.renga.socialapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public List<User> allUsers() {        
        return userRepository.findAll();       		
    }
}