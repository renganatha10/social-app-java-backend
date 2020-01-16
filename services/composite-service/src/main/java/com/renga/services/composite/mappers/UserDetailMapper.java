package com.renga.services.composite.mappers;

import com.renga.api.models.User;
import com.renga.services.composite.lookups.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserDetailMapper {
    @Mappings({})
    UserDetail toUserDetailFromUser(User user);
}
