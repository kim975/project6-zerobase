package com.zerobase.project6.user.domain.repository;

import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.model.StoreOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreOwnerRepository extends JpaRepository<StoreOwner, Long> {

    Optional<StoreOwner> findByLoginId(String loginId);

    Optional<StoreOwner> findByStoreOwnerToken(String storeOwnerToken);
}
