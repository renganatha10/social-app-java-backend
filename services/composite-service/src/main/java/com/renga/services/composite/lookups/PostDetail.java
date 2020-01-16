package com.renga.services.composite.lookups;

import com.renga.api.models.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetail extends Post {
    private Long commentCount;
    private Long likeCount;

}