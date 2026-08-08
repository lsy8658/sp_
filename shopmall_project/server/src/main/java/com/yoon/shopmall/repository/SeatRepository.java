package com.yoon.shopmall.repository;

import com.yoon.shopmall.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findByConcertId(Long concertId);
}
