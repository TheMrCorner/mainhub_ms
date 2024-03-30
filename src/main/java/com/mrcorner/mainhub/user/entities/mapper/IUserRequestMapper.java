package com.mrcorner.mainhub.user.entities.mapper;

import com.mrcorner.mainhub.authentication.dtos.UserRequest;
import com.mrcorner.mainhub.user.entities.UserInfo;
import com.mrcorner.mainhub.utils.IEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper extends IEntityMapper<UserRequest, UserInfo> {


    @Mapping(target = "id_user", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserRequest toDto(UserInfo entity);


    @Mapping(target = "id", ignore = true)
    UserInfo toEntity(UserRequest dto);
}
