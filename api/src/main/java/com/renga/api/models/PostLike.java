package com.renga.api.models;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PostLike extends BaseModel {
    private UUID userId;
    private UUID postId;
}