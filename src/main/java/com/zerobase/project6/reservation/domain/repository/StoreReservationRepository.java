package com.zerobase.project6.reservation.domain.repository;

import com.zerobase.project6.reservation.domain.model.StoreReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreReservationRepository extends JpaRepository<StoreReservation, Long> {
}
