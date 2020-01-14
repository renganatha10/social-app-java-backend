package com.renga.services.user.mappers;

import com.renga.api.models.User;
import com.renga.services.user.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "created_at", ignore = true),
            @Mapping(target = "updated_at", ignore = true)
    })
    User entityToApi(UserEntity entity);

    @Mappings({ })
    UserEntity apiToEntity(User api);

    List<User> entityListToApiList(List<UserEntity> entities);
    List<UserEntity> apiListToEntityList(List<User> apis);

}
