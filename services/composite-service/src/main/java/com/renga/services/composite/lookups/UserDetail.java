package com.renga.services.composite.lookups;

import com.renga.api.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail extends User {
    private Double followerCount;
    private Double followingCount;
    private Double postCount;
}