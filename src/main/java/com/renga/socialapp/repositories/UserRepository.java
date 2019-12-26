package com.renga.socialapp.repositories;

import java.util.UUID;

import com.renga.socialapp.models.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, UUID>{
}