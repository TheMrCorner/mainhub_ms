package com.mrcorner.journal.user.service;

import com.mrcorner.journal.authentication.dtos.UserRequest;
import com.mrcorner.journal.authentication.dtos.UserResponse;
import com.mrcorner.journal.user.entities.UserInfo;
import com.mrcorner.journal.user.entities.UserRole;

import java.util.List;

public interface IUserService {

    List<UserRole> findUserRoles(UserInfo user);

    void setRolesAndRelations(UserInfo user, UserRequest request) // setRolesAndRelations
    ;

    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUser();

    List<UserResponse> getAllUser();
}
