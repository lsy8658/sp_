package com.yoon.shopmall.repository;

import com.yoon.shopmall.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}