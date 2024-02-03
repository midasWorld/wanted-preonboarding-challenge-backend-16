package com.wanted.preonboarding.core.exception;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({
		IllegalStateException.class, IllegalArgumentException.class,
		TypeMismatchException.class, HttpMessageNotReadableException.class,
		MissingServletRequestParameterException.class, MultipartException.class,
	})
	public ResponseEntity<?> handleBadRequestException(Exception e) {
		log.info("Bad request exception occurred: {}", e.getMessage(), e);
		return newResponse(HttpStatus.BAD_REQUEST, e);
	}

	@ExceptionHandler(HttpMediaTypeException.class)
	public ResponseEntity<?> handleHttpMediaTypeException(Exception e) {
		return newResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, e);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotAllowedException(Exception e) {
		return newResponse(HttpStatus.METHOD_NOT_ALLOWED, e);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity handleServiceRuntimeException(BusinessException e) {
		if (e.getHttpStatus() == HttpStatus.BAD_REQUEST) {
			log.info(e.getMessage(), e);
			return newResponse(e.getHttpStatus(), e);
		}

		log.warn(e.getMessage(), e);
		return newResponse(e.getHttpStatus(), e);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity handleAllException(Exception e) {
		log.error("Unexpected error occurred. {}", e.getMessage(), e);
		return newResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}

	private ResponseEntity newResponse(HttpStatus status, Exception e) {
		return ResponseEntity.status(status)
			.body(ResponseHandler.builder()
				.message(e.getMessage())
				.statusCode(status)
			);
	}
}
