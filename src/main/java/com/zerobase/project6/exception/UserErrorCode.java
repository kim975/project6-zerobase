package com.zerobase.project6.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    NOT_FOUND_USER("E01001", "사용자가 존재 하지 않습니다."),
    NOT_FOUND_USER_TYPE("E01002", "존재 하지 않는 유저 타입 입니다.")
    ;

    private final String errorCode;
    private final String errorMessage;


}
