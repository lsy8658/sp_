package com.yoon.shopmall.controller;

import com.yoon.shopmall.domain.Seat;
import com.yoon.shopmall.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/concert/{concertId}")
    public ResponseEntity<List<Seat>> getSeats(
            @PathVariable("concertId") Long concertId
    ) {
        return ResponseEntity.ok(
                seatService.findByConcertId(concertId)
        );
    }
}