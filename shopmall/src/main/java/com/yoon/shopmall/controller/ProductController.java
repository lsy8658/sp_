package com.yoon.shopmall.controller;

import com.yoon.shopmall.domain_or_entity.Product;
import com.yoon.shopmall.dto.ProductRegisterRequest;
import com.yoon.shopmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody ProductRegisterRequest request) {
        Long id = productService.register(request.getName(), request.getPrice(), request.getStock(), request.getDescription());
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }
}
