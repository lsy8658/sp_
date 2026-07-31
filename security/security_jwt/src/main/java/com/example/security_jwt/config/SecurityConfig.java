package com.example.security_jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) {
        // 수많은 필터 중 CSRF 필터를 disable 시킴
        http.csrf(csrf -> csrf.disable());

        // 경로별 인가
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/**").permitAll());

        // 세션 설정 STATELESS
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 기본 로그인
        http.formLogin(login -> login.disable());

        return http.build();
    }
}

/*
    STATELESS는 세션을 생성하지도 않고 사용하지도 않는다는 뜻입니다.
 */