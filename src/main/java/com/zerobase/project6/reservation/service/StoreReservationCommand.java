package com.zerobase.project6.reservation.service;

import com.zerobase.project6.reservation.domain.model.StoreReservation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class StoreReservationCommand {

    @Data
    @Builder
    public static class ReservationRequest {
        private String customerToken;
        private String storeToken;
        private LocalDateTime reservationDate;

    }
}
