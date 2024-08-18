package com.sehoon.springmavensample.common.exception;

import com.sehoon.springmavensample.common.res.ResultCode;

import lombok.Getter;

/**
 * API 예외처리. 비즈니스 로직에 의한 exception 처리
 */
@Getter
public class ApiException extends RuntimeException {
    private ResultCode error;

    public ApiException(ResultCode e) {
        super(e.getMsg());
        this.error = e;
    }

    public ApiException(ResultCode e, String message) {
        super(message);
        this.error = e;
    }

}