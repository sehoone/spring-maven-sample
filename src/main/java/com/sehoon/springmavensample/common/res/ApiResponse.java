package com.sehoon.springmavensample.common.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse<T> {
	
	@Schema(description = "응답코드")
	private int code;
	@Schema(description = "응답메세지")
	private String msg;
	@Schema(description = "데이터")
	private T data;
	
	private ApiResponse() {
		this.code = ResultCode.SUCCESS.getCode();
		this.msg = ResultCode.SUCCESS.getMsg();
	}
	
	private ApiResponse(int code) {
		this.code = code;
	}
	
	private ApiResponse(T data) {
		this.data = data;
	}
	
	private ApiResponse(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	private ApiResponse(int code, T data) {
		this.code = code;
		this.data = data;
	}

	private ApiResponse(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public static <T> ApiResponse<T> ok() {
		return new ApiResponse<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
	}
	
	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
	}
	
	public static <T> ApiResponse<T> make(int code) {
		return new ApiResponse<T>(code);
	}
	
	public static <T> ApiResponse<T> make(int code, T data) {
		return new ApiResponse<T>(code, data);
	}

	public static <T> ApiResponse<T> make(int code, String msg, T data) {
		return new ApiResponse<T>(code, msg, data);
	}

	public static <T> ApiResponse<T> fail() {
		return new ApiResponse<T>(ResultCode.SERVER_ERROR.getCode());
	}
	
	public static <T> ApiResponse<T> fail(int code, String msg) {
		return new ApiResponse<T>(code, msg);
	}
	
	public static <T> ApiResponse<T> fail(int code, T data) {
		return new ApiResponse<T>(code, data);
	}
	
	public static <T> ApiResponse<T> fail(T data) {
		return new ApiResponse<T>(ResultCode.SERVER_ERROR.getCode(), data);
	}
	
}
