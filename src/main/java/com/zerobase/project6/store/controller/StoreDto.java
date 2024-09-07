package com.zerobase.project6.store.controller;

import com.zerobase.project6.store.service.StoreCommand;
import lombok.Data;

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
}
