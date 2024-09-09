package com.zerobase.project6.review.service;

import com.zerobase.project6.exception.*;
import com.zerobase.project6.reservation.domain.model.StoreReservation;
import com.zerobase.project6.reservation.domain.repository.StoreReservationRepository;
import com.zerobase.project6.review.domain.model.Review;
import com.zerobase.project6.review.domain.repository.ReviewRepository;
import com.zerobase.project6.store.domain.model.Store;
import com.zerobase.project6.store.domain.repository.StoreRepository;
import com.zerobase.project6.user.domain.model.Customer;
import com.zerobase.project6.user.domain.model.StoreOwner;
import com.zerobase.project6.user.domain.model.common.UserType;
import com.zerobase.project6.user.domain.repository.CustomerRepository;
import com.zerobase.project6.user.domain.repository.StoreOwnerRepository;
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
    private final StoreOwnerRepository storeOwnerRepository;

    /**
     *
     * 리뷰 등록 기능
     *
     * @param command
     * @return
     */
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
                .storeReservationId(storeReservation.getId())
                .build());


        addStoreStarPoint(store, review);

        return review.getReviewToken();
    }

    /**
     *
     * 리뷰 수정 기능
     *
     * @param command
     * @return
     */
    public String updateReview(ReviewCommand.UpdateReview command) {
        Review review = reviewRepository.findByReviewToken(command.getReviewToken())
                .orElseThrow(() -> new BaseException(ReviewErrorCode.NOT_FOUND_REVIEW));

        Customer customer = customerRepository.findByCustomerToken(command.getCustomerToken())
                .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

        if (!review.getCustomerId().equals(customer.getId())) throw new BaseException(ReviewErrorCode.DELETE_IS_REVIEWER_OR_STORE_OWNER);

        Store store = storeRepository.findById(review.getStoreId())
                .orElseThrow(() -> new BaseException(StoreErrorCode.NOT_FOUND_STORE));

        updateStoreStarPoint(store, command.getStarPoint(), review.getStarPoint());

        review.setText(command.getText());
        review.setStarPoint(command.getStarPoint());

        return review.getReviewToken();
    }

    /**
     *
     * 리뷰 등록시 유효성 검사
     *
     * @param customer
     * @param store
     * @param storeReservation
     */
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

    /**
     *
     * 별점 저장 기능
     *
     * @param store
     * @param review
     */
    private void addStoreStarPoint(Store store, Review review) {

        long starPointCount = store.getStarPointCount();
        double totalStartPoint = store.getStarPoint() * starPointCount;

        store.setStarPoint(totalStartPoint + review.getStarPoint() / (starPointCount + 1));
        store.setStarPointCount(starPointCount + 1);

        storeRepository.save(store);
    }

    /**
     *
     * 별점 수정 기능
     *
     * @param store
     * @param currentStarPoint
     * @param oldStarPoint
     */
    private void updateStoreStarPoint(Store store, double currentStarPoint, double oldStarPoint) {

        long starPointCount = store.getStarPointCount();
        double totalStartPoint = store.getStarPoint() * starPointCount;

        totalStartPoint = totalStartPoint - oldStarPoint + currentStarPoint;

        store.setStarPoint(totalStartPoint / starPointCount);

        storeRepository.save(store);
    }

    /**
     *
     * 리뷰 삭제 기능
     *
     * @param reviewToken
     * @param userToken
     * @param userType
     */
    public void deleteReview(String reviewToken, String userToken, UserType userType) {

        Review review = reviewRepository.findByReviewToken(reviewToken)
                .orElseThrow(() -> new BaseException(ReviewErrorCode.NOT_FOUND_REVIEW));

        if (UserType.CUSTOMER.equals(userType)) {

            Customer customer = customerRepository.findByCustomerToken(userToken)
                    .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

            if (!review.getCustomerId().equals(customer.getId())) throw new BaseException(ReviewErrorCode.DELETE_IS_REVIEWER_OR_STORE_OWNER);

            reviewRepository.delete(review);

        } else if(UserType.STORE_OWNER.equals(userType)) {

            StoreOwner storeOwner = storeOwnerRepository.findByStoreOwnerToken(userToken)
                    .orElseThrow(() -> new BaseException(UserErrorCode.NOT_FOUND_USER));

            Store reviewStore = storeRepository.findById(review.getStoreId())
                    .orElseThrow(() -> new BaseException(StoreErrorCode.NOT_FOUND_STORE));

            if (!reviewStore.getStoreOwnerId().equals(storeOwner.getId())) throw new BaseException(ReviewErrorCode.DELETE_IS_REVIEWER_OR_STORE_OWNER);

            reviewRepository.delete(review);

        } else {
            throw new BaseException(ReviewErrorCode.DELETE_IS_REVIEWER_OR_STORE_OWNER);
        }

    }


}
