package com.zerobase.project6.user.domain.repository;

import com.zerobase.project6.user.domain.model.StoreOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOwnerRepository extends JpaRepository<StoreOwner, Long> {
}
