package com.zerobase.project6.user.service;

import com.zerobase.project6.security.UserSecurity;
import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.model.StoreOwner;
import com.zerobase.project6.user.domain.model.common.UserRole;
import com.zerobase.project6.user.domain.model.common.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoginInfo {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CustomerInfo implements UserSecurity, UserDetails {
        private String loginId;
        private String customerToken;
        private List<UserRole> userRoles;

        public static LoginInfo.CustomerInfo of(Customer customer) {
            return LoginInfo.CustomerInfo.builder()
                    .loginId(customer.getLoginId())
                    .customerToken(customer.getCustomerToken())
                    .userRoles(customer.getRoles())
                    .build();
        }

        @Override
        public String getUserToken() {
            return customerToken;
        }

        @Override
        public String getUserType() {
            return UserType.CUSTOMER.toString();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return userRoles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.toString()))
                    .collect(Collectors.toList());
        }

        @Override
        public String getPassword() {
            return "";
        }

        @Override
        public String getUsername() {
            return customerToken;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreOwnerInfo implements UserSecurity, UserDetails {
        private String loginId;
        private String storeOwnerToken;
        private List<UserRole> userRoles;

        public static LoginInfo.StoreOwnerInfo of(StoreOwner storeOwner) {
            return LoginInfo.StoreOwnerInfo.builder()
                    .loginId(storeOwner.getLoginId())
                    .storeOwnerToken(storeOwner.getStoreOwnerToken())
                    .userRoles(storeOwner.getRoles())
                    .build();
        }

        @Override
        public String getUserToken() {
            return storeOwnerToken;
        }

        @Override
        public String getUserType() {
            return UserType.STORE_OWNER.toString();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return userRoles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.toString()))
                    .collect(Collectors.toList());
        }

        @Override
        public String getPassword() {
            return "";
        }

        @Override
        public String getUsername() {
            return storeOwnerToken;
        }
    }
}
