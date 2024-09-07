package com.zerobase.project6.store.domain.repository;

import com.zerobase.project6.store.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByStoreToken(String storeToken);
}
