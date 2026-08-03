package com.yoon.shopmall.service;

import com.yoon.shopmall.domain.Member;
import com.yoon.shopmall.domain.Order;
import com.yoon.shopmall.domain.OrderItem;
import com.yoon.shopmall.domain.Product;
import com.yoon.shopmall.repository.MemberRepository;
import com.yoon.shopmall.repository.OrderItemRepository;
import com.yoon.shopmall.repository.OrderRepository;
import com.yoon.shopmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long createOrder(Long memberId, Long productId, int quantity) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 없음"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("상품 없음"));

        int totalPrice = product.getPrice() * quantity;

        Order order = new Order(member, totalPrice);
        orderRepository.save(order);

        OrderItem orderItem = new OrderItem(order, product, quantity);
        orderItemRepository.save(orderItem);

        return order.getId();
    }
}