package com.yoon.shopmall.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "회원 ID는 필수입니다. ")
    private Long memberId;

    @NotNull(message = "상품 ID는 필수 입니다.")
    private Long productId;

    @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
    private int quantity;
}
