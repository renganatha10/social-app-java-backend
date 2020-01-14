package com.renga.services.post.mappers;

import com.renga.api.models.Comment;
import com.renga.services.post.models.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mappings({
            @Mapping(target = "created_at", ignore = true),
            @Mapping(target = "updated_at", ignore = true)
    })
    Comment entityToApi(CommentEntity entity);

    @Mappings({ })
    CommentEntity apiToEntity(Comment api);

    List<Comment> entityListToApiList(List<CommentEntity> entities);
    List<CommentEntity> apiListToEntityList(List<Comment> apis);
}
