package com.yoon.shopmall.service;

import com.yoon.shopmall.domain.*;
import com.yoon.shopmall.repository.MemberRepository;
import com.yoon.shopmall.repository.ReservationRepository;
import com.yoon.shopmall.repository.ReservationSeatRepository;
import com.yoon.shopmall.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationSeatRepository reservationSeatRepository;
    private final SeatRepository seatRepository;
    private final MemberRepository memberRepository;
    private final SeatHoldService seatHoldService;

    public Long reserve(Long memberId, List<Long> seatIds) {
        if (seatIds.size() > 4) {
            throw new RuntimeException("1인당 최대 4매까지 예매 가능합니다.");
        }

        for (Long seatId : seatIds) {
            boolean held = seatHoldService.holdSeat(seatId, memberId);
            if (!held) {
                throw new RuntimeException("이미 선택된 좌석이 있습니다.");
            }
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        int totalPrice = 0;
        Reservation reservation = Reservation.builder()
                .member(member)
                .status(ReservationStatus.HOLDING)
                .totalPrice(totalPrice)
                .build();
        reservationRepository.save(reservation);

        for (Long seatId : seatIds) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new RuntimeException("좌석 없음"));

            totalPrice += seat.getSeatGrade().getPrice();

            seat.changeStatus(SeatStatus.HOLDING);

            ReservationSeat reservationSeat = ReservationSeat.builder()
                    .reservation(reservation)
                    .seat(seat)
                    .build();
            reservationSeatRepository.save(reservationSeat);
        }

        reservation.updateTotalPrice(totalPrice);
        return reservation.getId();
    }
}
