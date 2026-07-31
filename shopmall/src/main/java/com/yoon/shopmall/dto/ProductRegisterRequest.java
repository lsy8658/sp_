package com.yoon.shopmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRegisterRequest {
    private String name;
    private int price;
    private int stock;
    private String description;
}
