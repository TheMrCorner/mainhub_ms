package com.mrcorner.mainhub.user.repositories;

import com.mrcorner.mainhub.user.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<UserRole, Long> {

}
