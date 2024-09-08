package com.zerobase.project6.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationErrorCode implements ErrorCode {

    NOT_FOUND_RESERVATION("E03001","예약이 존재 하지 않습니다."),
    TOO_EARLY_CHECK_IN("E03002","체크인은 예약시간 30분 전부터 가능합니다."),
    TOO_LATE_CHECK_IN("E03003","체크인은 예약시간 10분 전까지 가능합니다."),
    DIFFERENT_RESERVATION_CUSTOMER("E03004","예약자가 다릅니다.")
    ;

    private final String errorCode;
    private final String errorMessage;

}
