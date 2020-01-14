package com.renga.api.models;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class Comment extends BaseModel {
    private UUID userId;
    private String text;
}