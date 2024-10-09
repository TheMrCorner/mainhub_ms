package com.mrcorner.mainhub.user.service.impl;

import com.mrcorner.mainhub.authentication.dtos.UserRequest;
import com.mrcorner.mainhub.authentication.dtos.UserResponse;
import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
import com.mrcorner.mainhub.user.entities.UserInfo;
import com.mrcorner.mainhub.user.entities.UserRole;
import com.mrcorner.mainhub.user.entities.UsersRoles;
import com.mrcorner.mainhub.user.entities.mapper.IUserInfoMapper;
import com.mrcorner.mainhub.user.entities.mapper.IUserRequestMapper;
import com.mrcorner.mainhub.user.repositories.IRolesRepository;
import com.mrcorner.mainhub.user.repositories.IUserRepository;
import com.mrcorner.mainhub.user.repositories.IUsersRolesRepository;
import com.mrcorner.mainhub.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    IUserRepository userRepository;
    IUsersRolesRepository usersRolesRepository;
    IRolesRepository rolesRepository;
    IUserInfoMapper userInfoMapper;
    IUserRequestMapper userRequestMapper;

    @Override
    public List<UserRole> findUserRoles(UserInfo user) throws DataNotFoundException {
        List<Long> roles = usersRolesRepository.findAllRolesByIdUser(user.getId());
        List<UserRole> userRoles = rolesRepository.findAllById(roles);
        if(userRoles.isEmpty()){
            throw new DataNotFoundException("NO ROLES FOUND");
        } // if
        else{
            return userRoles;
        } // else
    } // findUserRoles

    @Override
    public void setRolesAndRelations(UserInfo user, UserRequest request){
        List<UserRole> userRoles = request.getRoles();
        for(UserRole userRole : userRoles){
            if(userRole.getId() != 0L){
                Optional<UserRole> userRoleOpt = rolesRepository.findById(userRole.getId());
                if(userRoleOpt.isPresent()){
                    userRoleOpt.get().setName(userRole.getName());
                    UsersRoles usersRoles = new UsersRoles();
                    usersRoles.setUser_info_id_user(user.getId());
                    usersRoles.setRoles_id_roles(userRole.getId());
                    usersRolesRepository.save(usersRoles);
                } // if
            } // if
            else{
                userRole = rolesRepository.save(userRole);
                UsersRoles usersRoles = new UsersRoles();
                usersRoles.setUser_info_id_user(user.getId());
                usersRoles.setRoles_id_roles(userRole.getId());
                usersRolesRepository.save(usersRoles);
            } // else
        } // for
    } // setRolesAndRelations

    @Override
    public UserResponse saveUser(UserRequest userRequest) throws InvalidDataException, DataNotFoundException{
        if(userRequest.getUsername() == null){
            throw new InvalidDataException("Parameter username is not found in request..!!");
        } // if
        else if(userRequest.getPassword() == null){
            throw new InvalidDataException("Parameter password is not found in request..!!");
        } // else if

        UserInfo user = null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userRequest.getPassword());
        user = userRequestMapper.toEntity(userRequest);
        user.setPassword(encodedPassword);
        if(user.getId() != 0L){
            Optional<UserInfo> oldUserOpt = userRepository.findById(userRequest.getId_user());
            if(oldUserOpt.isPresent()){
                UserInfo oldUser = oldUserOpt.get();
                oldUser.setId(user.getId());
                oldUser.setUsername(user.getUsername());
                oldUser.setPassword(user.getPassword());

                user = userRepository.save(oldUser);
                setRolesAndRelations(user, userRequest);
            }
            else{
                throw new DataNotFoundException("NO SALIÓ EL USER DE LA DATABASE");
            } // else
        } // if
        else{
            user = userRepository.save(user);
            setRolesAndRelations(user, userRequest);
        } // else
        UserResponse userResponse = userInfoMapper.toDto(user);
        userResponse.setRoles(findUserRoles(user));
        return userResponse;
    } // saveUser

    @Override
    public UserResponse getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String usernameFromAccessToken = userDetails.getUsername();
        UserInfo userInfo = userRepository.findByUsername(usernameFromAccessToken);
        return userInfoMapper.toDto(userInfo);
    } // getUser

    @Override
    public List<UserResponse> getAllUser() {
        List<UserInfo> users = userRepository.findAll();
        return userInfoMapper.toDtoList(users);
    } // getAllUser
} // UserServiceImpl
