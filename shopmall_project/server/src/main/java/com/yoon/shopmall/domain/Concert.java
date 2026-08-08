package com.yoon.shopmall.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime performanceDate;

    @Column(nullable = false)
    private String venue;

    private String posterImageUrl;

    private Integer runningTime;

    private Integer ageLimit;

    @Builder
    public Concert(Artist artist, String title, LocalDateTime performanceDate, String venue,
                   String posterImageUrl, Integer runningTime, Integer ageLimit) {
        this.artist = artist;
        this.title = title;
        this.performanceDate = performanceDate;
        this.venue = venue;
        this.posterImageUrl = posterImageUrl;
        this.runningTime = runningTime;
        this.ageLimit = ageLimit;
    }
}
