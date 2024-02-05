package com.mrcorner.journal.user.repositories;

import com.mrcorner.journal.user.entities.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsersRolesRepository extends JpaRepository<UsersRoles, Long> {

    @Query(value = "select * from users_roles where user_info_id_user = :idUser", nativeQuery = true)
    List<UsersRoles> findAllByIdUser(Long idUser);

    @Query(value = "select roles_id_roles from users_roles where user_info_id_user = :idUser", nativeQuery = true)
    List<Long> findAllRolesByIdUser(Long idUser);

    @Query(value = "select * from users_roles where roles_id_roles = :idRole", nativeQuery = true)
    List<UsersRoles> findAllByIdRole(Long idRole);
}
