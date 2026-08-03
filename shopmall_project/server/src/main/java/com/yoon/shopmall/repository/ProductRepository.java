package com.yoon.shopmall.repository;

import com.yoon.shopmall.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
