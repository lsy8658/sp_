package com.yoon.shopmall.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConcertRegisterRequest {
    @NotNull(message = "아티스트 ID는 필수입니다.")
    private Long artistId;

    @NotBlank(message = "콘서트명은 필수입니다.")
    private String title;

    @NotNull(message = "공연일시는 필수입니다.")
    private LocalDateTime performaceDate;

    @NotBlank(message = "공연장소는 필수입니다.")
    private String venue;

    private String posterImageUrl;
    private Integer runningTime;
    private Integer ageLimit;
}
