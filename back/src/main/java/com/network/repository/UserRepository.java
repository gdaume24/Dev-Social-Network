package com.network.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.network.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);

    Boolean existsByEmail(String email); 
}
