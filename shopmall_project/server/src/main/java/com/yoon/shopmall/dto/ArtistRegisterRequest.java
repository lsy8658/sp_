package com.yoon.shopmall.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistRegisterRequest {
    @NotBlank(message = "아티스트명은 필수입니다.")
    private String name;
    private String profileUrl;
}
