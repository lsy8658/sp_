package com.yoon.shopmall.service;

import com.yoon.shopmall.domain.Concert;
import com.yoon.shopmall.domain.Seat;
import com.yoon.shopmall.domain.SeatGrade;
import com.yoon.shopmall.domain.SeatStatus;
import com.yoon.shopmall.repository.ConcertRepository;
import com.yoon.shopmall.repository.SeatGradeRepository;
import com.yoon.shopmall.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final ConcertRepository concertRepository;
    private final SeatGradeRepository seatGradeRepository;

    public Long register(Long concertId, Long seatGradeId, String seatNumber) {
        Concert concert = concertRepository.findById(concertId)
                .orElseThrow(() -> new RuntimeException("콘서트 없음"));
        SeatGrade seatGrade = seatGradeRepository.findById(seatGradeId)
                .orElseThrow(() -> new RuntimeException("좌석등급 없음"));

        Seat seat = Seat.builder()
                .concert(concert)
                .seatGrade(seatGrade)
                .seatNumber(seatNumber)
                .status(SeatStatus.AVAILABLE)
                .build();

        return seatRepository.save(seat).getId();
    }

    public List<Seat> findByConcertId(Long concertId) {
        return seatRepository.findByConcertId(concertId);
    }
}