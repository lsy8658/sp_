package com.yoon.shopmall.controller;

import com.yoon.shopmall.config.JwtProvider;
import com.yoon.shopmall.domain_or_entity.Member;
import com.yoon.shopmall.dto.LoginRequest;
import com.yoon.shopmall.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class LoginController {
    private final LoginService loginService;
    private final JwtProvider jwtProvider;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Member member = loginService.login(request.getEmail(), request.getPassword());
        String token = jwtProvider.createToken(member.getEmail());
        return ResponseEntity.ok(token);
    }
}
