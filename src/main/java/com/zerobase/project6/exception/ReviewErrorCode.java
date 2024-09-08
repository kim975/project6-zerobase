package com.zerobase.project6.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {

    DIFFERENT_RESERVATION_CUSTOMER("E04001","리뷰작성자와 예약자가 다릅니다."),
    DIFFERENT_RESERVATION_STORE("E04002","리뷰 작성 요청한 가게와 예약한 가게가 다릅니다."),
    NOT_VISITED_STORE("E04003","방문후 리뷰를 작성하여야 합니다."),
    NOT_FOUND_REVIEW("E04004","리뷰를 찾을 수 없습니다."),
    DELETE_IS_REVIEWER_OR_STORE_OWNER("E04005", "삭제는 작성자 또는 가게 관리자만 삭제 할 수 있습니다.");

    private final String errorCode;
    private final String errorMessage;

}
