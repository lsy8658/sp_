package com.example.security_jwt.domain.user.service;

import com.example.security_jwt.domain.user.dto.UserRequestDTO;
import com.example.security_jwt.domain.user.entity.UserEntity;
import com.example.security_jwt.domain.user.entity.UserRole;
import com.example.security_jwt.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUsername(username).orElseThrow();
        return User.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .roles(entity.getRole().name())
                .build();
    }

}


/*
    @RequiredArgsConstructor 이걸 쓰든 아니면 직접 타이핑

    public UserService (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
*/
/*
    원인: entity.getRole()의 결과는 앞서 배운 순수한 자바 Enum 타입 데이터입니다.

    이유: 하지만 스프링 시큐리티의 .roles() 메서드는 무조건 텍스트인 문자열(String)만
    받을 수 있게 설계되어 있습니다.

    해결 (Depth의 이유): 그래서 자바 Enum이 기본으로 제공하는
    .name() 기능을 호출하여 "USER"나 "ADMIN" 같은 생글 자(문자열)로
    변환해서 쏙 넣어주는 것입니다.
*/