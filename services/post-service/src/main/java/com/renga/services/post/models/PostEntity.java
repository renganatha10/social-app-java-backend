package com.renga.services.post.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;


import org.hibernate.validator.constraints.URL;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "post", schema = "public")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PostEntity extends BaseModel {
    public PostEntity(UUID postId) {
        this.setId(postId);
    }

    private UUID userId;

    private String text;

    @URL
    private String videoUrl;

    @URL
    private String photoUrl;
}