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

    /**
     *
     * 가게 상세 정보 search
     *
     * @param storeToken
     * @return
     */
    @GetMapping("/store/{storeToken}/detail")
    public ResponseEntity<StoreDto.StoreInfoResponse> searchStore(
            @PathVariable String storeToken
    ) {
        return ResponseEntity.ok(StoreDto.StoreInfoResponse.of(storeService.getStoreByStoreToken(storeToken)));
    }

    /**
     *
     * 가게 목록 search
     *
     * pageable 사용 : sort 시 starPoint, name 등으로 정렬 가능
     *
     * @param pageable
     * @return
     */
    @GetMapping("/stores")
    public ResponseEntity<Page<StoreDto.StoreInfoResponse>> searchStoreList(
            Pageable pageable
    ) {
        return ResponseEntity.ok(StoreDto.StoreInfoResponse.of(storeService.getAllStore(pageable)));
    }
}
