package com.example.security_jwt.domain.user.repository;

import com.example.security_jwt.domain.user.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}

/*
     기본 등록된 Bean 활용 및 SecurityConfig 설정만으로 대부분 코딩 없이 사용 가능
     다만~! Json으로 username/password 를 보내주기 때문에 받는 필터를 직접 구현
*/