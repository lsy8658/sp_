package com.yoon.shopmall.controller;

import com.yoon.shopmall.dto.ReviewRequest;
import com.yoon.shopmall.dto.ReviewUpdateRequest;
import com.yoon.shopmall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Long> createReview(
            @RequestBody ReviewRequest request
    ) {
        Long reviewId = reviewService.createReview(
                request.getMemberId(),
                request.getProductId(),
                request.getContent(),
                request.getRating()
        );
        return ResponseEntity.ok(reviewId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update (
            @PathVariable("id") Long id,
            @RequestBody ReviewUpdateRequest request
    ) {
        reviewService.update(id, request.getContent(), request.getRating());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return  ResponseEntity.ok().build();
    }
}