package com.yoon.shopmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignUpRequest {
    private String email;
    private String password;
    private String nickname;
}
