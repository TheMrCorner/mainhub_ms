package com.mrcorner.mainhub.user.entities.mapper;

import com.mrcorner.mainhub.authentication.dtos.UserResponse;
import com.mrcorner.mainhub.user.entities.UserInfo;
import com.mrcorner.mainhub.utils.IEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserInfoMapper  extends IEntityMapper<UserResponse, UserInfo> {


    @Mapping(target = "password", ignore = true)
    UserInfo toEntity(UserResponse dto);


    @Mapping(target = "roles", ignore = true)
    UserResponse toDto(UserInfo entity);

    List<UserResponse> toDtoList(List<UserInfo> users);
}
