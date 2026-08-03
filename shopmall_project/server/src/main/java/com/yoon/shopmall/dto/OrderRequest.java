package com.yoon.shopmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Long memberId;
    private Long productId;
    private int quantity;
}
