package com.mrcorner.journal.user.repositories;

import com.mrcorner.journal.user.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}
