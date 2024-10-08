package com.zerobase.project6.user.service;

import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.model.StoreOwner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * service에서 return용 DTO
 */
public class SignUpInfo {

    /**
     * 사용자 회원가입 return DTO
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CustomerInfo {
        private String loginId;
        private String email;
        private String customerToken;

        public static SignUpInfo.CustomerInfo of(Customer customer) {
            return SignUpInfo.CustomerInfo.builder()
                    .loginId(customer.getLoginId())
                    .email(customer.getEmail())
                    .customerToken(customer.getCustomerToken())
                    .build();
        }
    }

    /**
     * 가게 관리자 회원가입 return DTO
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreOwnerInfo {
        private String loginId;
        private String email;
        private String storeOwnerToken;

        public static SignUpInfo.StoreOwnerInfo of(StoreOwner storeOwner) {
            return SignUpInfo.StoreOwnerInfo.builder()
                    .loginId(storeOwner.getLoginId())
                    .email(storeOwner.getEmail())
                    .storeOwnerToken(storeOwner.getStoreOwnerToken())
                    .build();
        }
    }

}
