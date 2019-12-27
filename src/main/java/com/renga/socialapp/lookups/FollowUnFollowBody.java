package com.renga.socialapp.lookups;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowUnFollowBody {
    private String followerId;
    private String userId;
}