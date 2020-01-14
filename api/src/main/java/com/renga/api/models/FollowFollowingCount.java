package com.renga.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowFollowingCount {
    private int followerCount;
    private int followingCount;
}
