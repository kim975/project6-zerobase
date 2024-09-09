package com.zerobase.project6.user.controller;

import com.zerobase.project6.security.TokenProvider;
import com.zerobase.project6.user.service.LoginInfo;
import com.zerobase.project6.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService longinService;
    private final TokenProvider tokenProvider;

    /**
     * 
     * 사용자용 로그인 
     * 
     * @param request
     * @return
     */
    @PostMapping("/customer")
    public ResponseEntity<String> loginCustomer(
            @RequestBody LoginDto request
    ) {
        LoginInfo.CustomerInfo customerInfo = longinService.loginCustomer(request.toCommand());
        return ResponseEntity.ok(tokenProvider.generateToken(customerInfo));
    }

    /**
     * 
     * 가게 관리자 로그인
     * 
     * @param request
     * @return
     */
    @PostMapping("/store-owner")
    public ResponseEntity<String> loginStoreOwner(
            @RequestBody LoginDto request
    ) {
        LoginInfo.StoreOwnerInfo storeOwnerInfo = longinService.loginStoreOwner(request.toCommand());
        return ResponseEntity.ok(tokenProvider.generateToken(storeOwnerInfo));
    }

}
