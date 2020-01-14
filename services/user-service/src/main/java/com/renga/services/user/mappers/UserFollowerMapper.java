package com.renga.services.user.mappers;

import com.renga.api.models.UserFollower;
import com.renga.services.user.models.UserFollowerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserFollowerMapper {
    @Mappings({
            @Mapping(target = "created_at", ignore = true),
            @Mapping(target = "updated_at", ignore = true)
    })
    UserFollower toApi(UserFollowerEntity userFollowingEntity);

    @Mappings({ })
    UserFollowerEntity toEntity(UserFollower userFollowingEntity);


    List<UserFollower> entityListToApiList(List<UserFollowerEntity> entities);
    List<UserFollowerEntity> apiListToEntityList(List<UserFollower> apis);
}
