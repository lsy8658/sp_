package com.yoon.shopmall.repository;

import com.yoon.shopmall.domain.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
