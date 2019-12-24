package com.renga.socialapp.repository;

import com.renga.socialapp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    
}