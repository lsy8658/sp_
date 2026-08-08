package com.yoon.shopmall.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SeatHoldService {
    private final RedissonClient redissonClient;
    private final StringRedisTemplate redisTemplate;

    public boolean holdSeat(Long seatId, Long memberId) {
        RLock lock = redissonClient.getLock("seat-lock" + seatId);

        try {
            boolean acquired = lock.tryLock(3, TimeUnit.SECONDS);
            if (!acquired) {
                return false;
            }

            String key = "seat-hold:" +  seatId;
            Boolean alreadyHeld = redisTemplate.hasKey(key);
            if (Boolean.TRUE.equals(alreadyHeld)) {
                return false;
            }

            redisTemplate.opsForValue().set(key, String
                    .valueOf(memberId), 5, TimeUnit.MINUTES);
            return true;
        }catch (InterruptedException e) {
            throw new RuntimeException("lock 처리 중 오류");
        } finally {
            lock.unlock();
        }
    }
}
