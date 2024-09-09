package com.zerobase.project6.user.service;

import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.model.StoreOwner;
import lombok.Builder;
import lombok.Data;

/**
 * service에서 사용하는 DTO
 */
public class SignUpCommand {

    /**
     * 사용자 회원가입 DTO
     */
    @Data
    @Builder
    public static class RegisterCustomer {

        private String loginId;
        private String password;
        private String email;

        public Customer toEntity() {
            return Customer.builder()
                    .loginId(loginId)
                    .password(password)
                    .email(email)
                    .build();
        }

    }

    /**
     * 가게 관리자 회원가입 DTO
     */
    @Data
    @Builder
    public static class RegisterStoreOwner {
        private String loginId;
        private String password;
        private String email;

        public StoreOwner toEntity() {
            return StoreOwner.builder()
                    .loginId(loginId)
                    .password(password)
                    .email(email)
                    .build();
        }
    }
}
