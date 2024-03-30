package com.mrcorner.mainhub.user.service;

import com.mrcorner.mainhub.authentication.dtos.UserRequest;
import com.mrcorner.mainhub.authentication.dtos.UserResponse;
import com.mrcorner.mainhub.user.entities.UserInfo;
import com.mrcorner.mainhub.user.entities.UserRole;

import java.util.List;

public interface IUserService {

    List<UserRole> findUserRoles(UserInfo user);

    void setRolesAndRelations(UserInfo user, UserRequest request) // setRolesAndRelations
    ;

    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUser();

    List<UserResponse> getAllUser();
}
