package com.sehoon.springmavensample.module.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sehoon.springmavensample.module.user.domain.User;

/**
 * Spring Data SQL repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByAccount(String account);
}
