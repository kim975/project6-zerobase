package com.zerobase.project6.user.domain.model;

import com.zerobase.project6.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOwner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;
    private String password;

    private String email;

    @Column(unique = true)
    private String storeOwnerToken;
}
