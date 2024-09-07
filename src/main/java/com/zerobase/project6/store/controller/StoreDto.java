package com.zerobase.project6.store.controller;

import com.zerobase.project6.store.service.StoreCommand;
import com.zerobase.project6.store.service.StoreInfo;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

public class StoreDto {

    @Data
    public static class RegisterStore {
        private String name;
        private String address;
        private String description;

        public StoreCommand.RegisterStore toCommand() {
            return StoreCommand.RegisterStore.builder()
                    .name(name)
                    .address(address)
                    .description(description)
                    .build();
        }
    }

    @Data
    public static class UpdateStore {

        private String storeToken;
        private String name;
        private String address;
        private String description;

        public StoreCommand.UpdateStore toCommand() {
            return StoreCommand.UpdateStore.builder()
                    .storeToken(storeToken)
                    .name(name)
                    .address(address)
                    .description(description)
                    .build();
        }
    }

    @Data
    public static class DeleteStore {

        private String storeToken;

        public StoreCommand.DeleteStore toCommand() {
            return StoreCommand.DeleteStore.builder()
                    .storeToken(storeToken)
                    .build();
        }
    }

    @Data
    @Builder
    public static class StoreInfoResponse {
        private String storeToken;
        private String name;
        private String address;
        private String description;
        private Double starPoint;

        public static StoreInfoResponse of(StoreInfo storeInfo) {
            return StoreInfoResponse.builder()
                    .storeToken(storeInfo.getStoreToken())
                    .name(storeInfo.getName())
                    .address(storeInfo.getAddress())
                    .description(storeInfo.getDescription())
                    .starPoint(storeInfo.getStarPoint())
                    .build();
        }

        public static Page<StoreInfoResponse> of(Page<StoreInfo> storeInfoPage) {
            return storeInfoPage.map(StoreInfoResponse::of);
        }
    }
}
