package com.main.java.exceptions;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

	private final String field;
	private final String errorCode;

	public CustomException(String field, String errorCode, String message) {
		super(message);
		this.field = field;
		this.errorCode = errorCode;
	}

}
