package com.yoon.shopmall.dto;

import lombok.Getter;

@Getter
public class MemberSignUpRequest {
    private String email;
    private String password;
    private String nickname;
}
