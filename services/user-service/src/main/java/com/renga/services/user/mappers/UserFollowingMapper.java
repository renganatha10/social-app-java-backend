package com.renga.services.user.mappers;

import com.renga.api.models.UserFollowing;
import com.renga.services.user.models.UserFollowingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserFollowingMapper {
    @Mappings({
            @Mapping(target = "created_at", ignore = true),
            @Mapping(target = "updated_at", ignore = true)
    })
    UserFollowing toApi(UserFollowingEntity userFollowingEntity);

    @Mappings({ })
    UserFollowingEntity toEntity(UserFollowing userFollowingEntity);


    List<UserFollowing> entityListToApiList(List<UserFollowingEntity> entities);
    List<UserFollowingEntity> apiListToEntityList(List<UserFollowing> apis);

}
