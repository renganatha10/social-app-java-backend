package com.renga.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeCount {
    private int commentCount;
    private int postLikeCount;
    private UUID postId;
}