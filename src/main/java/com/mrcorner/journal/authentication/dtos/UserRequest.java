package com.mrcorner.journal.authentication.dtos;

import com.mrcorner.journal.user.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    private Long id_user;
    private String username;
    private String password;
    private List<UserRole> roles;

}
