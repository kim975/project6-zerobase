package com.zerobase.project6.user.service;

import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.model.StoreOwner;
import com.zerobase.project6.user.domain.repository.CustomerRepository;
import com.zerobase.project6.user.domain.repository.StoreOwnerRepository;
import com.zerobase.project6.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final CustomerRepository customerRepository;
    private final StoreOwnerRepository storeOwnerRepository;

    public SignUpInfo.CustomerInfo signUpCustomer(SignUpCommand.RegisterCustomer command) {

        Customer customer = command.toEntity();
        customer.setCustomerToken(TokenGenerator.randomUUID());

        return SignUpInfo.CustomerInfo.of(customerRepository.save(customer));
    }

    public SignUpInfo.StoreOwnerInfo signUpStoreOwner(SignUpCommand.RegisterStoreOwner command) {

        StoreOwner storeOwner = command.toEntity();
        storeOwner.setStoreOwnerToken(TokenGenerator.randomUUID());

        return SignUpInfo.StoreOwnerInfo.of(storeOwnerRepository.save(storeOwner));
    }
}
