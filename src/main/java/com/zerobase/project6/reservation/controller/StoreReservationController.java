package com.zerobase.project6.reservation.controller;

import com.zerobase.project6.reservation.service.StoreReservationCommand;
import com.zerobase.project6.reservation.service.StoreReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store/reservation")
@PreAuthorize("hasRole('CUSTOMER')")
public class StoreReservationController {

    private final StoreReservationService storeReservationService;

    @PostMapping
    public ResponseEntity<String> makeReservation(
            Authentication authentication,
            @RequestBody StoreReservationDto.ReservationRequest request
    ) {
        return ResponseEntity.ok(
                storeReservationService.makeReservation(request.toCommand(authentication.getName()))
        );
    }

    @GetMapping("/{reservationToken}/check-in")
    public ResponseEntity<Void> checkInReservation(
            Authentication authentication,
            @PathVariable String reservationToken
    ) {
        storeReservationService.checkInReservation(StoreReservationCommand.CheckInReservation.builder()
                .reservationToken(reservationToken)
                .customerToken(authentication.getName())
                .build());
        return ResponseEntity.ok().build();
    }
}
