package com.sehoon.springmavensample.common.advice;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sehoon.springmavensample.common.exception.ApiException;
import com.sehoon.springmavensample.common.res.ApiResponse;
import com.sehoon.springmavensample.common.res.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * ApiException 처리. 비즈니스 로직에서 발생하는 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(HttpServletRequest request, final ApiException e) {

        String message = StringUtils.defaultString(e.getMessage(), e.getError().getMsg());
        // e.getError().getCode();
        // return ResponseEntity
        //         .status(e.getError().getStatus())
        //         .body(ApiExceptionDTO.builder()
        //                 .code(e.getError().getCode())
        //                 .msg(message)
        //                 .build());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.fail(e.getError().getCode(), message));    
    }

    /**
     * RuntimeException 처리. 런타임 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
        log.error("RuntimeException ", e);
        return ResponseEntity
                .status(ResultCode.BAD_REQUEST.getStatus())
                .build();
        // return ResponseEntity
        //         .status(HttpStatus.OK)
        //         .body(ApiResponse.fail(ResultCode.BAD_REQUEST.getCode(), e.getMessage()));    
    }

    /**
     * InsufficientAuthenticationException 처리. 인증 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({InsufficientAuthenticationException.class})
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(HttpServletRequest request, final InsufficientAuthenticationException e) {
        log.error("InsufficientAuthenticationException ", e);
        return ResponseEntity
        .status(ResultCode.UNAUTHORIZED.getStatus())
        .build();
        // return ResponseEntity
        //         .status(ResultCode.UNAUTHORIZED.getStatus())
        //         .body(ApiExceptionDTO.builder()
        //                 .code(ResultCode.UNAUTHORIZED.getCode())
        //                 .msg(e.getMessage())
        //                 .build());
        // return ResponseEntity
        //         .status(HttpStatus.OK)
        //         .body(ApiResponse.fail(ResultCode.UNAUTHORIZED.getCode(), e.getMessage()));    
    }

    /**
     * AccessDeniedException 처리. 권한 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {
        log.error("AccessDeniedException ", e);
        return ResponseEntity
        .status(ResultCode.UNAUTHORIZED.getStatus())
        .build();
        // return ResponseEntity
        //         .status(ResultCode.UNAUTHORIZED.getStatus())
        //         .body(ApiExceptionDTO.builder()
        //                 .code(ResultCode.UNAUTHORIZED.getCode())
        //                 .msg(e.getMessage())
        //                 .build());
        // return ResponseEntity
        //         .status(HttpStatus.OK)
        //         .body(ApiResponse.fail(ResultCode.UNAUTHORIZED.getCode(), e.getMessage()));    
    }
    
    /**
     * MethodArgumentNotValidException 처리. Validation 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(HttpServletRequest request, final MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException ", e);
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String field = e.getBindingResult().getFieldError().getField();
    
        // return ResponseEntity
        //         .status(HttpStatus.OK)
        //         .body(ApiExceptionDTO.builder()
        //                 .code(ResultCode.BAD_REQUEST.getCode())
        //                 .msg("요청값 '"+ field +"' 가 유효하지 않습니다. "+message)
        //                 .build());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.fail(ResultCode.BAD_REQUEST.getCode(), "요청값 '"+ field +"' 가 유효하지 않습니다. "+message));    
    }

    /**
     * MissingServletRequestParameterException 처리. 필수 파라미터 누락 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(HttpServletRequest request, final MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException ", e);
        // return ResponseEntity
        //         .status(HttpStatus.OK)
        //         .body(ApiExceptionDTO.builder()
        //                 .code(ResultCode.BAD_REQUEST.getCode())
        //                 .msg(e.getMessage())
        //                 .build());
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiResponse.fail(ResultCode.BAD_REQUEST.getCode(), e.getMessage()));
    }

    /**
     * ConstraintViolationException 처리. Validation 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(HttpServletRequest request, final ConstraintViolationException e) {
        log.error("ConstraintViolationException ", e);

        // return ResponseEntity
        //         .status(HttpStatus.OK)
        //         .body(ApiExceptionDTO.builder()
        //                 .code(ResultCode.BAD_REQUEST.getCode())
        //                 .msg(e.getMessage())
        //                 .build());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.fail(ResultCode.BAD_REQUEST.getCode(), e.getMessage()));    
    }

    /**
     * Exception 처리. 기타 예외 처리
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(HttpServletRequest request, final Exception e) {
        log.error("Exception ", e);
        return ResponseEntity
                .status(ResultCode.SERVER_ERROR.getStatus())
                .build();
        // return ResponseEntity
        //         .status(HttpStatus.OK)
        //         .body(ApiResponse.fail(ResultCode.SERVER_ERROR.getCode(), e.getMessage()));    
    }
}