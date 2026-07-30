package com.example.security_jwt.domain.user.repository;

import com.example.security_jwt.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
