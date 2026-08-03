package com.yoon.shopmall.controller;

import com.yoon.shopmall.dto.OrderRequest;
import com.yoon.shopmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderRequest request) {
        Long orderId = orderService.createOrder(
                request.getMemberId(),
                request.getProductId(),
                request.getQuantity()
        );

        return ResponseEntity.ok(orderId);
    }
}
