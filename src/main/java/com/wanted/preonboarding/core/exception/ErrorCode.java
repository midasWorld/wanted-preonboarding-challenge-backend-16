package com.wanted.preonboarding.core.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	// Performance
	PERFORMANCE_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 공연은 존재하지 않습니다. id={0}"),
	PERFORMANCE_NOT_BOOKABLE(HttpStatus.BAD_REQUEST, "해당 공연은 예매가 불가능합니다."),
	BALANCE_NOT_ENOUGH(HttpStatus.BAD_REQUEST, "사용 가능한 잔액이 부족합니다. 잔액을 확인하고 다시 시도해주세요."),

	RESERVATION_CANCEL_NOTICE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "해당 번호로 예약 가능 알림이 이미 등록되었습니다.")
	;

	private HttpStatus httpStatus;
	private String message;

	ErrorCode(HttpStatus statusCode, String message) {
		this.httpStatus = statusCode;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage(Object... args) {
		return MessageFormat.format(message, args);
	}
}
