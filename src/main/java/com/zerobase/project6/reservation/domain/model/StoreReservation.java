package com.zerobase.project6.reservation.domain.model;

import com.zerobase.project6.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreReservation extends BaseEntity {

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
