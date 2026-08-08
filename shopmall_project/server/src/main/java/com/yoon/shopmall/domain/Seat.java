package com.yoon.shopmall.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "concert_id")
    private Concert concert;

    @ManyToOne
    @JoinColumn(name = "seat_grade_id")
    private SeatGrade seatGrade;

    @Column(nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

    @Builder
    public Seat (Concert concert, SeatGrade seatGrade, String seatNumber, SeatStatus status) {
        this.concert = concert;
        this.seatGrade = seatGrade;
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public void changeStatus (SeatStatus status) {
        this.status = status;
    }
}
