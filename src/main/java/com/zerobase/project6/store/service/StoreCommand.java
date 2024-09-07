package com.zerobase.project6.store.service;

import com.zerobase.project6.store.domain.model.Store;
import lombok.Builder;
import lombok.Data;

public class StoreCommand {

    @Data
    @Builder
    public static class RegisterStore {

        private String name;
        private String address;
        private String description;

        public Store toEntity(Long storeOwnerId) {
            return Store.builder()
                    .storeOwnerId(storeOwnerId)
                    .name(name)
                    .address(address)
                    .description(description)
                    .build();
        }

    }

}
