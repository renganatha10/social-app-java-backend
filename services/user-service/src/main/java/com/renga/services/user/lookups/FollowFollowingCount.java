package com.renga.services.user.lookups;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowFollowingCount {
    private int followerCount;
    private int followingCount;
}
