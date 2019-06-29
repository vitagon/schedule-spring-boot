package com.vitgon.schedule.controller.rest.advice;

import lombok.Getter;

@Getter
public class FieldValidationException extends Exception {
	
	private String fieldName;
	private String i18nCode;

	private static final long serialVersionUID = -7847132655894025371L;

	public FieldValidationException(String fieldName, String i18nCode) {
		super();
		this.fieldName = fieldName;
		this.i18nCode = i18nCode;
	}

	public FieldValidationException(Throwable cause) {
		super(cause);
	}
}
