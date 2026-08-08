package com.yoon.shopmall.service;


import com.yoon.shopmall.domain.Artist;
import com.yoon.shopmall.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public Long register(String name, String profileImageUrl) {
        Artist artist = Artist.builder()
                .name(name)
                .profileImageUrl(profileImageUrl)
                .build();
        return artistRepository.save(artist).getId();
    }
}
