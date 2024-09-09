package com.zerobase.project6.store.controller;

import com.zerobase.project6.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
@PreAuthorize("hasRole('STORE_OWNER')")
public class StoreController {

    private final StoreService storeService;

    /**
     *
     * 가게 등록
     *
     * @param authentication
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<String> registerStore(
            Authentication authentication,
            @RequestBody StoreDto.RegisterStore request
    ) {
        String storeOwnerToken = authentication.getName();
        return ResponseEntity.ok(storeService.registerStore(request.toCommand(), storeOwnerToken));
    }

    /**
     * 
     * 가게 수정 기능
     * 
     * @param authentication
     * @param request
     * @return
     */
    @PutMapping
    public ResponseEntity<String> updateStore(
            Authentication authentication,
            @RequestBody StoreDto.UpdateStore request
    ) {
        String storeOwnerToken = authentication.getName();
        return ResponseEntity.ok(storeService.updateStore(request.toCommand(), storeOwnerToken));
    }

    /**
     * 가게 삭제 기능
     * @param authentication
     * @param request
     * @return
     */
    @DeleteMapping
    public ResponseEntity<String> deleteStore(
            Authentication authentication,
            @RequestBody StoreDto.DeleteStore request
    ) {
        String storeOwnerToken = authentication.getName();
        storeService.deleteStore(request.toCommand(), storeOwnerToken);
        return ResponseEntity.ok().build();
    }

}
