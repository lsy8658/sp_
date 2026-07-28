package com.yoon.shopmall.service;

import com.yoon.shopmall.domain_or_entity.Member;
import com.yoon.shopmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long signUp(String email, String password, String nickname) {
        memberRepository.findByEmail(email)
                .ifPresent(m -> {throw new RuntimeException("이미 가입된 이메일");});

        Member member = Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
        return memberRepository.save(member).getId();
    }
}
