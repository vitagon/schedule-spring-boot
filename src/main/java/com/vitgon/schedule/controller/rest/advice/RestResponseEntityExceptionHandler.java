package com.vitgon.schedule.controller.rest.advice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vitgon.schedule.model.ApiError;

@ControllerAdvice("com.vitgon.schedule.controller.rest")
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiError> handleAllExceptions(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ApiError>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(new Date(), "Resource Not Found", "The resource was not found!");
		return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, List<String>> errors = new HashMap<>();
		
		List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
		for (ObjectError error : objectErrors) {
			FieldError fieldError = (FieldError) error;
			String fieldName = fieldError.getField();
			
			List<String> errorMessages = null;
			if (errors.containsKey(fieldName)) {
				errorMessages = errors.get(fieldName);
			} else {
				errorMessages = new ArrayList<>();
			}
			errorMessages.add(fieldError.getDefaultMessage());
			if (!errors.containsKey(fieldName)) {
				errors.put(fieldName, errorMessages);
			}
		}
		ApiError apiError = new ApiError(new Date(), "Validation Failed", errors);
		return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
	}
}
