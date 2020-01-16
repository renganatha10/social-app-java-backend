package com.renga.services.composite.mappers;

import com.renga.api.models.Post;
import com.renga.services.composite.lookups.PostDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PostDetailMapper {
    @Mappings({})
    PostDetail toPostDetailFromPost(Post user);
}
