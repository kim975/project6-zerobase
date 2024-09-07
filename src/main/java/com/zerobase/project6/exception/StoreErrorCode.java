package com.zerobase.project6.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements ErrorCode {

    NOT_FOUND_STORE("E02001","가게가 존재 하지 않습니다."),
    DIFFERENT_STORE_OWNER("E02002","가게를 등록한 사용자가 아닙니다.");

    private final String errorCode;
    private final String errorMessage;
}
