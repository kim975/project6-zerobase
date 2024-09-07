package com.zerobase.project6.store.controller;

import com.zerobase.project6.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreSearchController {

    private final StoreService storeService;

    @GetMapping("/store/{storeToken}/detail")
    public ResponseEntity<StoreDto.StoreInfoResponse> searchStore(
            @PathVariable String storeToken
    ) {
        return ResponseEntity.ok(StoreDto.StoreInfoResponse.of(storeService.getStoreByStoreToken(storeToken)));
    }

    @GetMapping("/stores")
    public ResponseEntity<Page<StoreDto.StoreInfoResponse>> searchStoreList(
            Pageable pageable
    ) {
        return ResponseEntity.ok(StoreDto.StoreInfoResponse.of(storeService.getAllStore(pageable)));
    }
}
