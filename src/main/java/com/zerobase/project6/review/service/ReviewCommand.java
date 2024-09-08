package com.zerobase.project6.review.service;

import lombok.Builder;
import lombok.Data;

public class ReviewCommand {

    @Data
    @Builder
    public static class RegisterReview {
        private String customerToken;
        private String storeToken;
        private String reservationToken;

        private String text;
        private Double starPoint;
    }

}
