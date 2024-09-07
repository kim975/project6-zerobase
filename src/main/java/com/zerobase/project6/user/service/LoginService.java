package com.zerobase.project6.user.service;

import com.zerobase.project6.exception.BaseException;
import com.zerobase.project6.exception.UserErrorCode;
import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.model.StoreOwner;
import com.zerobase.project6.user.domain.model.common.UserType;
import com.zerobase.project6.user.domain.repository.CustomerRepository;
import com.zerobase.project6.user.domain.repository.StoreOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final CustomerRepository customerRepository;
    private final StoreOwnerRepository storeOwnerRepository;

    public LoginInfo.CustomerInfo loginCustomer(LoginCommand command) {

        Customer customer = customerRepository.findByLoginId(command.getLoginId())
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        if (!customer.getPassword().equals(command.getPassword())) {
            throw new BaseException(UserErrorCode.NOT_FOUND_USER);
        }

        return LoginInfo.CustomerInfo.of(customer);

    }

    public LoginInfo.StoreOwnerInfo loginStoreOwner(LoginCommand command) {

        StoreOwner storeOwner = storeOwnerRepository.findByLoginId(command.getLoginId())
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        if (!storeOwner.getPassword().equals(command.getPassword())) {
            throw new BaseException(UserErrorCode.NOT_FOUND_USER);
        }

        return LoginInfo.StoreOwnerInfo.of(storeOwner);

    }

    public UserDetails loadUserByUserToken(String userToken, UserType userType) {

        if (UserType.CUSTOMER.equals(userType)) {

            Customer customer = customerRepository.findByCustomerToken(userToken)
                    .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

            return LoginInfo.CustomerInfo.of(customer);

        } else if (UserType.STORE_OWNER.equals(userType)) {

            StoreOwner storeOwner = storeOwnerRepository.findByStoreOwnerToken(userToken)
                    .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

            return LoginInfo.StoreOwnerInfo.of(storeOwner);

        } else {
            throw new BaseException(UserErrorCode.NOT_FOUND_USER_TYPE);
        }
    }
}
