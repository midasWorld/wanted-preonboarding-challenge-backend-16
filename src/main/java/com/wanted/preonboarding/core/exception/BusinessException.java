package com.wanted.preonboarding.core.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode, Object... args) {
		super(errorCode.getMessage(args));
		this.errorCode = errorCode;
	}

	public HttpStatus getHttpStatus() {
		return errorCode.getHttpStatus();
	}
}
