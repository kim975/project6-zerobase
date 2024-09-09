package com.zerobase.project6.review.service;

import lombok.Builder;
import lombok.Data;

/**
 * service에서 사용하는 DTO
 */
public class ReviewCommand {


    /**
     * 리뷰 등록용 DTO
     */
    @Data
    @Builder
    public static class RegisterReview {
        private String customerToken;
        private String storeToken;
        private String reservationToken;

        private String text;
        private Double starPoint;
    }

    /**
     * 리뷰 수정 DTO
     */
    @Data
    @Builder
    public static class UpdateReview {
        private String customerToken;
        private String reviewToken;

        private String text;
        private Double starPoint;
    }
}
