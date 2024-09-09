package com.zerobase.project6.user.service;

import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.model.StoreOwner;
import com.zerobase.project6.user.domain.model.common.UserRole;
import com.zerobase.project6.user.domain.repository.CustomerRepository;
import com.zerobase.project6.user.domain.repository.StoreOwnerRepository;
import com.zerobase.project6.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final CustomerRepository customerRepository;
    private final StoreOwnerRepository storeOwnerRepository;

    /**
     *
     * 사용자 회원가입 기능
     *
     * @param command
     * @return
     */
    public SignUpInfo.CustomerInfo signUpCustomer(SignUpCommand.RegisterCustomer command) {

        Customer customer = command.toEntity();
        customer.setCustomerToken(TokenGenerator.randomUUID());
        customer.setRoles(List.of(UserRole.ROLE_CUSTOMER));

        return SignUpInfo.CustomerInfo.of(customerRepository.save(customer));
    }

    /**
     *
     * 가게 관리자 회원 가입 기능
     *
     * @param command
     * @return
     */
    public SignUpInfo.StoreOwnerInfo signUpStoreOwner(SignUpCommand.RegisterStoreOwner command) {

        StoreOwner storeOwner = command.toEntity();
        storeOwner.setStoreOwnerToken(TokenGenerator.randomUUID());
        storeOwner.setRoles(List.of(UserRole.ROLE_STORE_OWNER));

        return SignUpInfo.StoreOwnerInfo.of(storeOwnerRepository.save(storeOwner));
    }
}
