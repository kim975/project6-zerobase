package com.zerobase.project6.review.domain.model;

import com.zerobase.project6.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reviewToken;

    private Long customerId;
    private Long storeId;
    private Long storeReservationId;

    private String text;
    private Double starPoint;

}
