package com.sehoon.springmavensample.common.advice;

import java.nio.file.AccessDeniedException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sehoon.springmavensample.common.dto.ApiExceptionDTO;
import com.sehoon.springmavensample.common.enums.ExceptionEnum;
import com.sehoon.springmavensample.common.exception.ApiException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * ApiException 처리. 비즈니스 로직에서 발생하는 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({ ApiException.class })
    public ResponseEntity<ApiExceptionDTO> exceptionHandler(HttpServletRequest request, final ApiException e) {

        String message = StringUtils.defaultString(e.getMessage(), e.getError().getMessage());

        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ApiExceptionDTO.builder()
                        .errorCode(e.getError().getCode())
                        .errorMessage(message)
                        .build());
    }

    /**
     * RuntimeException 처리. 런타임 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<ApiExceptionDTO> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.BAD_REQUEST_EXCEPTION.getStatus())
                .body(ApiExceptionDTO.builder()
                        .errorCode(ExceptionEnum.BAD_REQUEST_EXCEPTION.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

    /**
     * AccessDeniedException 처리. 권한이 없는 경우 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<ApiExceptionDTO> exceptionHandler(HttpServletRequest request,
            final AccessDeniedException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getStatus())
                .body(ApiExceptionDTO.builder()
                        .errorCode(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

    /**
     * MethodArgumentNotValidException 처리. @Valid 검증 실패시 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<ApiExceptionDTO> exceptionHandler(HttpServletRequest request,
            final MethodArgumentNotValidException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.ARGUMENT_NOT_VALIDATION_EXCEPTION.getStatus())
                .body(ApiExceptionDTO.builder()
                        .errorCode(ExceptionEnum.ARGUMENT_NOT_VALIDATION_EXCEPTION.getCode())
                        .errorMessage(e.getBindingResult().getAllErrors().get(0)
                                .getDefaultMessage())
                        .build());
    }

    /**
     * Exception 처리. 그 외 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ApiExceptionDTO> exceptionHandler(HttpServletRequest request, final Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiExceptionDTO.builder()
                        .errorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }
}