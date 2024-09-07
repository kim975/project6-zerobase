package com.zerobase.project6.store.service;

import com.zerobase.project6.store.domain.model.Store;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreInfo {

    private String storeToken;
    private String name;
    private String address;
    private String description;
    private Double starPoint;

    public static StoreInfo of(Store store) {
        return StoreInfo.builder()
                .storeToken(store.getStoreToken())
                .name(store.getName())
                .address(store.getAddress())
                .description(store.getDescription())
                .starPoint(store.getStarPoint())
                .build();
    }

}
