package com.example.security_jwt.domain.user.service;

import com.example.security_jwt.domain.user.dto.UserRequestDTO;
import com.example.security_jwt.domain.user.entity.UserEntity;
import com.example.security_jwt.domain.user.entity.UserRole;
import com.example.security_jwt.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(UserRequestDTO dto) {

        String username = dto.username();
        String password = dto.password();

        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setRole(UserRole.USER);

        userRepository.save(entity);
    }
}


/*
    @RequiredArgsConstructor 이걸 쓰든 아니면 직접 타이핑

    public UserService (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
*/
