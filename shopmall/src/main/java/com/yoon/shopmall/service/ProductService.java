package com.yoon.shopmall.service;

import com.yoon.shopmall.domain_or_entity.Product;
import com.yoon.shopmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Long register(String name, int price, int stock, String description) {
        Product product = Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .description(description)
                .build();
        return productRepository.save(product).getId();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("상품 없음")
        );

    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
