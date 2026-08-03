package com.yoon.shopmall.repository;

import com.yoon.shopmall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
