package com.zerobase.project6.user.service;

import com.zerobase.project6.user.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpInfo {

    private String loginId;
    private String email;
    private String customerToken;

    public static SignUpInfo of(Customer customer) {
        return SignUpInfo.builder()
                .loginId(customer.getLoginId())
                .email(customer.getEmail())
                .customerToken(customer.getCustomerToken())
                .build();
    }
}
