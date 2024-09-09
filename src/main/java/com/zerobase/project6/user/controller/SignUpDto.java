package com.zerobase.project6.user.controller;

import com.zerobase.project6.user.service.SignUpCommand;
import com.zerobase.project6.user.service.SignUpInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * client request, response DTO
 */
public class SignUpDto {

    /**
     * 사용자 회원가입 request DTO
     */
    @Data
    public static class RegisterCustomerRequest {
        private String loginId;
        private String password;
        private String email;

        public SignUpCommand.RegisterCustomer toCommand() {
            return SignUpCommand.RegisterCustomer.builder()
                    .loginId(loginId)
                    .password(password)
                    .email(email)
                    .build();
        }
    }

    /**
     * 사용자 회원가입 response DTO
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RegisterCustomerResponse {
        private String loginId;
        private String email;
        private String customerToken;

        public static RegisterCustomerResponse of(SignUpInfo.CustomerInfo signUpInfo) {
            return RegisterCustomerResponse.builder()
                    .loginId(signUpInfo.getLoginId())
                    .email(signUpInfo.getEmail())
                    .customerToken(signUpInfo.getCustomerToken())
                    .build();
        }
    }

    /**
     * 가게 관리자 회원가입 request DTO
     */
    @Data
    public static class RegisterStoreOwnerRequest {
        private String loginId;
        private String password;
        private String email;

        public SignUpCommand.RegisterStoreOwner toCommand() {
            return SignUpCommand.RegisterStoreOwner.builder()
                    .loginId(loginId)
                    .password(password)
                    .email(email)
                    .build();
        }
    }

    /**
     * 가게 관리자 회원가입 response DTO
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RegisterStoreOwnerResponse {
        private String loginId;
        private String email;
        private String storeOwnerToken;

        public static RegisterStoreOwnerResponse of(SignUpInfo.StoreOwnerInfo signUpInfo) {
            return RegisterStoreOwnerResponse.builder()
                    .loginId(signUpInfo.getLoginId())
                    .email(signUpInfo.getEmail())
                    .storeOwnerToken(signUpInfo.getStoreOwnerToken())
                    .build();
        }
    }
}
