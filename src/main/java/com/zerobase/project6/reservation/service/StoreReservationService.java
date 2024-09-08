package com.zerobase.project6.reservation.service;

import com.zerobase.project6.exception.BaseException;
import com.zerobase.project6.exception.StoreErrorCode;
import com.zerobase.project6.exception.UserErrorCode;
import com.zerobase.project6.reservation.domain.model.StoreReservation;
import com.zerobase.project6.reservation.domain.repository.StoreReservationRepository;
import com.zerobase.project6.store.domain.model.Store;
import com.zerobase.project6.store.domain.repository.StoreRepository;
import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.repository.CustomerRepository;
import com.zerobase.project6.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreReservationService {

    private final StoreReservationRepository storeReservationRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;

    public String makeReservation(StoreReservationCommand.ReservationRequest command) {

        Customer customer = customerRepository.findByCustomerToken(command.getCustomerToken())
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        Store store = storeRepository.findByStoreToken(command.getStoreToken())
                .orElseThrow(() -> new BaseException(StoreErrorCode.NOT_FOUND_STORE));

        StoreReservation reservation = StoreReservation.builder()
                .storeId(store.getId())
                .customerId(customer.getId())
                .reservationDate(command.getReservationDate())
                .visitedYn(false)
                .reservationToken(TokenGenerator.randomUUID())
                .build();

        return storeReservationRepository.save(reservation).getReservationToken();
    }

}
