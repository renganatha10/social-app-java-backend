package com.renga.services.user.repositories;

import java.util.UUID;

import com.renga.services.user.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, UUID>  {
}