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
}
