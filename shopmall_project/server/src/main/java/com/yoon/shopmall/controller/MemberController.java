package com.yoon.shopmall.controller;


import com.yoon.shopmall.dto.MemberSignUpRequest;
import com.yoon.shopmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody MemberSignUpRequest request) {
        Long id = memberService.signUp(request.getEmail(), request.getPassword(), request.getNickname());
        return ResponseEntity.ok(id);
    }
}
