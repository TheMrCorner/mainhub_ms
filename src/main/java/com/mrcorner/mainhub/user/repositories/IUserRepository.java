package com.mrcorner.mainhub.user.repositories;

import com.mrcorner.mainhub.user.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}
