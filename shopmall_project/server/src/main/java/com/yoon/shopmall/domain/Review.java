package com.yoon.shopmall.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int rating;

    private String content;

    @Builder
    public Review(Member member, Product product, String content, int rating) {
        this.member = member;
        this.product = product;
        this.content = content;
        this.rating = rating;
    }

    public void update(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }
}
