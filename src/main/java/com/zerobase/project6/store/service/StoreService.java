package com.zerobase.project6.store.service;

import com.zerobase.project6.exception.BaseException;
import com.zerobase.project6.exception.UserErrorCode;
import com.zerobase.project6.store.domain.model.Store;
import com.zerobase.project6.store.domain.repository.StoreRepository;
import com.zerobase.project6.user.domain.model.StoreOwner;
import com.zerobase.project6.user.domain.repository.StoreOwnerRepository;
import com.zerobase.project6.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreOwnerRepository storeOwnerRepository;

    public String registerStore(StoreCommand.RegisterStore command, String storeOwnerToken) {

        StoreOwner storeOwner = storeOwnerRepository.findByStoreOwnerToken(storeOwnerToken)
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        Store store = command.toEntity(storeOwner.getId());
        store.setStoreToken(TokenGenerator.randomUUID());

        return storeRepository.save(store).getStoreToken();
    }

}
