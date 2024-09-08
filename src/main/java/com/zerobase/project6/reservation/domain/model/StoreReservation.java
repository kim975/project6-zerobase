package com.zerobase.project6.reservation.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reservationToken;

    private Long customerId;
    private Long storeId;

    private LocalDateTime reservationDate;

    private boolean visitedYn;
}
