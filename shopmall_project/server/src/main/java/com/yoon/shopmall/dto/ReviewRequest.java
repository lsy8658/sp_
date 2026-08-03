package com.yoon.shopmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {
    private Long memberId;
    private Long productId;
    private String content;
    private int rating;
}