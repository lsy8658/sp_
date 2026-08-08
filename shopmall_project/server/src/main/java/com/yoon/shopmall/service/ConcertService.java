package com.yoon.shopmall.service;

import com.yoon.shopmall.domain.Artist;
import com.yoon.shopmall.domain.Concert;
import com.yoon.shopmall.repository.ArtistRepository;
import com.yoon.shopmall.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final ArtistRepository artistRepository;

    public Long register(Long artistId, String title, LocalDateTime performanceDate,
                         String venue, String posterImageUrl, Integer runningTime, Integer ageLimit) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new RuntimeException("아티스트 없음"));

        Concert concert = Concert.builder()
                .artist(artist)
                .title(title)
                .performanceDate(performanceDate)
                .venue(venue)
                .posterImageUrl(posterImageUrl)
                .runningTime(runningTime)
                .ageLimit(ageLimit)
                .build();

        return concertRepository.save(concert).getId();
    }

    public List<Concert> findAll() {
        return concertRepository.findAll();
    }

    public Concert findById(Long id) {
        return concertRepository.findById(id).orElseThrow(() -> new RuntimeException("콘서트 없음"));
    }
}
