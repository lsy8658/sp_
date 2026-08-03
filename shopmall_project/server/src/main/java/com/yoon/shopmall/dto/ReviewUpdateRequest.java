package com.yoon.shopmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewUpdateRequest {
    private String content;
    private int rating;
}