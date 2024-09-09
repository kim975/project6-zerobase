package com.zerobase.project6.reservation.service;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * service에서 사용하는 DTO
 */
public class StoreReservationCommand {

    /**
     * 예약하기 DTO
     */
    @Data
    @Builder
    public static class ReservationRequest {
        private String customerToken;
        private String storeToken;
        private LocalDateTime reservationDate;
    }

    /**
     * check-in DTO
     */
    @Data
    @Builder
    public static class CheckInReservation {
        private String customerToken;
        private String reservationToken;
    }
}
