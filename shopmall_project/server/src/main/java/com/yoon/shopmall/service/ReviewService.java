package com.yoon.shopmall.service;

import com.yoon.shopmall.domain.Member;
import com.yoon.shopmall.domain.Product;
import com.yoon.shopmall.domain.Review;
import com.yoon.shopmall.repository.MemberRepository;
import com.yoon.shopmall.repository.ProductRepository;
import com.yoon.shopmall.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long createReview(Long memberId, Long productId, String content, int rating) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("회원 없음"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("상품 없음"));

        Review review = Review.builder()
                .member(member)
                .product(product)
                .content(content)
                .rating(rating)
                .build();

        return reviewRepository.save(review).getId();
    }

    @Transactional
    public void update(Long id, String content, int rating) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("리뷰 없음"));
        review.update(content, rating);
    }

    @Transactional
    public void delete(Long id) {
        reviewRepository.deleteById(id);

    }}
