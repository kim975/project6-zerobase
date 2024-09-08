package com.zerobase.project6.reservation.controller;

import com.zerobase.project6.reservation.service.StoreReservationCommand;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class StoreReservationDto {

    @Data
    public static class ReservationRequest {

        private String storeToken;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime reservationDate;

        public StoreReservationCommand.ReservationRequest toCommand(String customerToken) {
            return StoreReservationCommand.ReservationRequest.builder()
                    .customerToken(customerToken)
                    .storeToken(storeToken)
                    .reservationDate(reservationDate)
                    .build();
        }

    }

}
