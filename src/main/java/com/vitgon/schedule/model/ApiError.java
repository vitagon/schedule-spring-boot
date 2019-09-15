package com.vitgon.schedule.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


public class ApiError {
	
	private Date timestamp;
	private String message;
	private Object details;
	
	public ApiError(Date timestamp, String message, Object details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getDetails() {
		return details;
	}
	public void setDetails(Object details) {
		this.details = details;
	}
}
