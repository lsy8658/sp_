package com.yoon.shopmall.controller;

import com.yoon.shopmall.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Long> reserve(
            @AuthenticationPrincipal Long memberId,
            @RequestBody List<Long> seatIds
    ) {
        Long reservationId = reservationService.reserve(memberId, seatIds);
        return ResponseEntity.ok(reservationId);
    }
}