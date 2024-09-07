package com.zerobase.project6.store.domain.model;

import com.zerobase.project6.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String storeToken;

    private Long storeOwnerId;
    private String name;
    private String address;
    private String description;
    private Double starPoint;

}
