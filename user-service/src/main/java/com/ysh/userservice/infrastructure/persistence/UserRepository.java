package com.ysh.userservice.infrastructure.persistence;

import com.ysh.userservice.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    long countById(String id);
}
