package com.knock_knu.KNOCK_KNU_BE.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    WRONG_DOOR_NAME(HttpStatus.BAD_REQUEST, "DE_001", "잘못된 문 이름입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
