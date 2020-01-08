package com.renga.services.user.lookups;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowUnFollowBody {
    private String followerId;
    private String userId;
}