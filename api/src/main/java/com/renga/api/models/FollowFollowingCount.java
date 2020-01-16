package com.renga.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowFollowingCount {
    private int followerCount;
    private int followingCount;
}
