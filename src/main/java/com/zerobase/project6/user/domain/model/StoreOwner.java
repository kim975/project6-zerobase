package com.zerobase.project6.user.domain.model;

import com.zerobase.project6.common.BaseEntity;
import com.zerobase.project6.user.domain.model.common.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<UserRole> roles = new ArrayList<>();
}
