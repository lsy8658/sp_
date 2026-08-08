package com.yoon.shopmall.controller;


import com.yoon.shopmall.dto.ArtistResisterRequest;
import com.yoon.shopmall.service.ArtistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping
    public ResponseEntity<Long> register(@Valid @RequestBody ArtistResisterRequest request) {
        Long id = artistService.register(request.getName(), request.getProfileUrl());
        return ResponseEntity.ok(id);
    }
}
