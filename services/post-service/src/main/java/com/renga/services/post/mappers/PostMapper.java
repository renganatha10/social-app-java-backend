package com.renga.services.post.mappers;

import com.renga.api.models.Post;
import com.renga.services.post.models.CommentEntity;
import com.renga.services.post.models.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mappings({
            @Mapping(target = "created_at", ignore = true),
            @Mapping(target = "updated_at", ignore = true)
    })
    Post entityToApi(PostEntity entity);

    @Mappings({ })
    PostEntity apiToEntity(Post api);

    List<Post> entityListToApiList(List<PostEntity> entities);
    List<PostEntity> apiListToEntityList(List<Post> apis);
}
