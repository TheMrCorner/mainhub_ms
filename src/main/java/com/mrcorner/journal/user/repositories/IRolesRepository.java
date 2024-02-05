package com.mrcorner.journal.user.repositories;

import com.mrcorner.journal.user.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<UserRole, Long> {

}
