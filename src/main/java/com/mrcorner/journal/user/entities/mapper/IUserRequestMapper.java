package com.mrcorner.journal.user.entities.mapper;

import com.mrcorner.journal.authentication.dtos.UserRequest;
import com.mrcorner.journal.user.entities.UserInfo;
import com.mrcorner.journal.utils.IEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper extends IEntityMapper<UserRequest, UserInfo> {

    UserRequest toDto(UserInfo entity);

    UserInfo toEntity(UserRequest dto);
}
