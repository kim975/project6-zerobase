package com.zerobase.project6.user.controller;

import com.zerobase.project6.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {

    private final SignUpService signUpService;

    /**
     *
     * 사용자 회원가입
     *
     * @param request
     * @return
     */
    @PostMapping("/customer")
    public ResponseEntity<SignUpDto.RegisterCustomerResponse> registerCustomer(
            @RequestBody SignUpDto.RegisterCustomerRequest request
    ) {
                return ResponseEntity.ok(
                        SignUpDto.RegisterCustomerResponse.of(signUpService.signUpCustomer(request.toCommand()))
                );
    }

    /**
     *
     * 가게 관리자 회원가입
     *
     * @param request
     * @return
     */
    @PostMapping("/store-owner")
    public ResponseEntity<SignUpDto.RegisterStoreOwnerResponse> registerStoreOwner(
            @RequestBody SignUpDto.RegisterStoreOwnerRequest request
    ) {
        return ResponseEntity.ok(
                SignUpDto.RegisterStoreOwnerResponse.of(signUpService.signUpStoreOwner(request.toCommand()))
        );
    }
}
