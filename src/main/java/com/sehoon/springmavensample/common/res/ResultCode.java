package com.sehoon.springmavensample.common.res;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ResultCode {
	SUCCESS(HttpStatus.OK, 0, "SUCCESS"),
	SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "SERVER_ERROR"),
	BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "BAD_REQUEST"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 401, "UNAUTHORIZED"),
	FORBIDDEN(HttpStatus.FORBIDDEN, 403, "FORBIDDEN"),
	NOT_FOUND(HttpStatus.NOT_FOUND, 404, "NOT_FOUND")
	;
	
    private final HttpStatus status;
    private final int code;
    private String msg;

    ResultCode(HttpStatus status, int code) {
        this.status = status;
        this.code = code;
    }

    ResultCode(HttpStatus status, int code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }
}
