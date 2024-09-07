package com.zerobase.project6.user.controller;

import com.zerobase.project6.user.service.SignUpCommand;
import com.zerobase.project6.user.service.SignUpInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SignUpDto {

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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RegisterCustomerResponse {
        private String loginId;
        private String email;
        private String customerToken;

        public static RegisterCustomerResponse of(SignUpInfo signUpInfo) {
            return RegisterCustomerResponse.builder()
                    .loginId(signUpInfo.getLoginId())
                    .email(signUpInfo.getEmail())
                    .customerToken(signUpInfo.getCustomerToken())
                    .build();
        }
    }
}
