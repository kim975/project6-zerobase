package com.zerobase.project6.user.service;

import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.model.StoreOwner;
import lombok.Builder;
import lombok.Data;

public class SignUpCommand {

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
