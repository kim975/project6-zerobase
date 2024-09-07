package com.zerobase.project6.user.service;

import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.repository.CustomerRepository;
import com.zerobase.project6.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final CustomerRepository customerRepository;

    public SignUpInfo signUpCustomer(SignUpCommand.RegisterCustomer command) {

        Customer customer = command.toEntity();
        customer.setCustomerToken(TokenGenerator.randomUUID());

        return SignUpInfo.of(customerRepository.save(customer));
    }
}
