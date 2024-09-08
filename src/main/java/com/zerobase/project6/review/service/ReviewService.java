package com.zerobase.project6.review.service;

import com.zerobase.project6.exception.*;
import com.zerobase.project6.reservation.domain.model.StoreReservation;
import com.zerobase.project6.reservation.domain.repository.StoreReservationRepository;
import com.zerobase.project6.review.domain.model.Review;
import com.zerobase.project6.review.domain.repository.ReviewRepository;
import com.zerobase.project6.store.domain.model.Store;
import com.zerobase.project6.store.domain.repository.StoreRepository;
import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.repository.CustomerRepository;
import com.zerobase.project6.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final StoreReservationRepository storeReservationRepository;

    public String registerReview(ReviewCommand.RegisterReview command) {

        Customer customer = customerRepository.findByCustomerToken(command.getCustomerToken())
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        Store store = storeRepository.findByStoreToken(command.getStoreToken())
                .orElseThrow(() -> new BaseException(StoreErrorCode.NOT_FOUND_STORE));

        StoreReservation storeReservation = storeReservationRepository.findByReservationToken(command.getReservationToken())
                .orElseThrow(() -> new BaseException(ReservationErrorCode.NOT_FOUND_RESERVATION));

        registerReviewValidation(customer, store, storeReservation);

        Review review = reviewRepository.save(Review.builder()
                .reviewToken(TokenGenerator.randomUUID())
                .text(command.getText())
                .customerId(customer.getId())
                .starPoint(command.getStarPoint())
                .storeId(store.getId())
                .reservationId(storeReservation.getId())
                .build());


        addStoreStarPoint(store, review);

        return review.getReviewToken();
    }

    private void registerReviewValidation(Customer customer, Store store, StoreReservation storeReservation) {

        if (!customer.getId().equals(storeReservation.getCustomerId())) {
            throw new BaseException(ReviewErrorCode.DIFFERENT_RESERVATION_CUSTOMER);
        }

        if (!store.getId().equals(storeReservation.getStoreId())) {
            throw new BaseException(ReviewErrorCode.DIFFERENT_RESERVATION_STORE);
        }

        if (!storeReservation.isVisitedYn()) {
            throw new BaseException(ReviewErrorCode.NOT_VISITED_STORE);
        }
    }

    private void addStoreStarPoint(Store store, Review review) {

        long startPointCount = store.getStarPointCount();
        double totalStartPoint = store.getStarPoint() * startPointCount;

        store.setStarPoint(totalStartPoint + review.getStarPoint() / (startPointCount + 1));
        store.setStarPointCount(startPointCount + 1);

        storeRepository.save(store);
    }

}
