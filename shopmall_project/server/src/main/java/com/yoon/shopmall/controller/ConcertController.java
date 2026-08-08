package com.yoon.shopmall.controller;

import com.yoon.shopmall.domain.Concert;
import com.yoon.shopmall.dto.ConcertRegisterRequest;
import com.yoon.shopmall.service.ConcertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/concerts")
public class ConcertController {

    private final ConcertService concertService;

    @PostMapping
    public ResponseEntity<Long> register(@Valid @RequestBody ConcertRegisterRequest request) {
        Long id = concertService.register(
                request.getArtistId(),
                request.getTitle(),
                request.getPerformanceDate(),
                request.getVenue(),
                request.getPosterImageUrl(),
                request.getRunningTime(),
                request.getAgeLimit()
        );
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<List<Concert>> getAllConcerts() {
        return ResponseEntity.ok(concertService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concert> getConcert(@PathVariable("id") Long id) {
        return ResponseEntity.ok(concertService.findById(id));
    }
}