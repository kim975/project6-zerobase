package com.zerobase.project6.store.controller;

import com.zerobase.project6.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
@PreAuthorize("hasRole('STORE_OWNER')")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<String> registerStore(
            Authentication authentication,
            @RequestBody StoreDto.RegisterStore request
    ) {
        String storeOwnerToken = authentication.getName();
        return ResponseEntity.ok(storeService.registerStore(request.toCommand(), storeOwnerToken));
    }

}
