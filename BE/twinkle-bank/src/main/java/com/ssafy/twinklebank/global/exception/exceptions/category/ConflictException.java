package com.ssafy.twinklebank.global.exception.exceptions.category;

import com.ssafy.twinklebank.global.exception.response.ErrorCode;

/**
 * 409
 * 리소스 등의 충돌
 */
public class ConflictException extends BankRuntimeException {
	protected static final String MESSAGE_KEY = "error.Conflict";
	public ConflictException(String detailMessageKey, ErrorCode errorCode, Object[] params) {
		super(MESSAGE_KEY + "." + detailMessageKey, errorCode, params);
	}
}
