package com.zerobase.project6.store.service;

import com.zerobase.project6.exception.BaseException;
import com.zerobase.project6.exception.StoreErrorCode;
import com.zerobase.project6.exception.UserErrorCode;
import com.zerobase.project6.store.domain.model.Store;
import com.zerobase.project6.store.domain.repository.StoreRepository;
import com.zerobase.project6.user.domain.model.StoreOwner;
import com.zerobase.project6.user.domain.repository.StoreOwnerRepository;
import com.zerobase.project6.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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

    public String updateStore(StoreCommand.UpdateStore command, String storeOwnerToken) {

        Store store = storeRepository.findByStoreToken(command.getStoreToken())
                .orElseThrow(() -> new BaseException(StoreErrorCode.NOT_FOUND_STORE));

        StoreOwner storeOwner = storeOwnerRepository.findByStoreOwnerToken(storeOwnerToken)
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        if (!store.getStoreOwnerId().equals(storeOwner.getId())) {
            throw new BaseException(StoreErrorCode.DIFFERENT_STORE_OWNER);
        }

        store.setName(command.getName());
        store.setAddress(command.getAddress());
        store.setDescription(command.getDescription());

        return storeRepository.save(store).getStoreToken();
    }

    public void deleteStore(StoreCommand.DeleteStore command, String storeOwnerToken) {

        Store store = storeRepository.findByStoreToken(command.getStoreToken())
                .orElseThrow(() -> new BaseException(StoreErrorCode.NOT_FOUND_STORE));

        StoreOwner storeOwner = storeOwnerRepository.findByStoreOwnerToken(storeOwnerToken)
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        if (!store.getStoreOwnerId().equals(storeOwner.getId())) {
            throw new BaseException(StoreErrorCode.DIFFERENT_STORE_OWNER);
        }

        storeRepository.delete(store);
    }

    public Page<StoreInfo> getAllStore(Pageable pageable) {
        return storeRepository.findAll(pageable).map(StoreInfo::of);
    }

    public StoreInfo getStoreByStoreToken(String storeToken) {
        return StoreInfo.of(
                storeRepository.findByStoreToken(storeToken)
                        .orElseThrow(() -> new BaseException(StoreErrorCode.NOT_FOUND_STORE))
        );
    }

}
