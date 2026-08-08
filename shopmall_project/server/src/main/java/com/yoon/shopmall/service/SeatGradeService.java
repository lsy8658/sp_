package com.yoon.shopmall.service;

import com.yoon.shopmall.domain.Concert;
import com.yoon.shopmall.domain.SeatGrade;
import com.yoon.shopmall.repository.ConcertRepository;
import com.yoon.shopmall.repository.SeatGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatGradeService {
    private final SeatGradeRepository seatGradeRepository;
    private final ConcertRepository concertRepository;

    public Long register(Long concertId, String name, int price) {
        Concert concert = concertRepository.findById(concertId).orElseThrow(() -> new RuntimeException("콘서트 없음"));

        SeatGrade seatGrade = SeatGrade.builder()
                .concert(concert)
                .name(name)
                .price(price)
                .build();

        return seatGradeRepository.save(seatGrade).getId();
    }
}
