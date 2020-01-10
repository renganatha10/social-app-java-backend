package com.renga.services.post.lookups;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentLikeCount {
    private int commentCount;
    private int postLikeCount;
}