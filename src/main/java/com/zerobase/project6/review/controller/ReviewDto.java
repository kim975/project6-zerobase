package com.zerobase.project6.review.controller;

import com.zerobase.project6.review.service.ReviewCommand;
import lombok.Data;

/**
 * client request, response DTO
 */
public class ReviewDto {

    /**
     * 리뷰 등록 DTO
     */
    @Data
    public static class RegisterReview {
        private String storeToken;
        private String reservationToken;

        private String text;
        private Double starPoint;

        public ReviewCommand.RegisterReview toCommand(String customerToken) {
            return ReviewCommand.RegisterReview.builder()
                    .customerToken(customerToken)
                    .storeToken(storeToken)
                    .reservationToken(reservationToken)
                    .text(text)
                    .starPoint(starPoint)
                    .build();
        }
    }

    /**
     * 리뷰 수정 DTO
     */
    @Data
    public static class UpdateReview {
        private String reviewToken;

        private String text;
        private Double starPoint;

        public ReviewCommand.UpdateReview toCommand(String customerToken) {
            return ReviewCommand.UpdateReview.builder()
                    .customerToken(customerToken)
                    .reviewToken(reviewToken)
                    .text(text)
                    .starPoint(starPoint)
                    .build();
        }
    }
}
