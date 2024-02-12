package com.mrcorner.journal.user.entities.mapper;

import com.mrcorner.journal.authentication.dtos.UserResponse;
import com.mrcorner.journal.user.entities.UserInfo;
import com.mrcorner.journal.utils.IEntityMapper;
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
