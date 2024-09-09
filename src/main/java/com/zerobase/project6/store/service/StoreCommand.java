package com.zerobase.project6.store.service;

import com.zerobase.project6.store.domain.model.Store;
import lombok.Builder;
import lombok.Data;

/**
 * service에서 사용하는 DTO
 */
public class StoreCommand {

    /**
     * 가게 등록 DTO
     */
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

    /**
     * 가게 수정 DTO
     */
    @Data
    @Builder
    public static class UpdateStore {

        private String storeToken;
        private String name;
        private String address;
        private String description;

    }

    /**
     * 가게 삭제 DTO
     */
    @Data
    @Builder
    public static class DeleteStore {
        private String storeToken;
    }
}
