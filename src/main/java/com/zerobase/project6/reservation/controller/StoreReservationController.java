package com.zerobase.project6.reservation.controller;

import com.zerobase.project6.reservation.service.StoreReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
