package com.vitgon.schedule.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


public class ApiSuccess {
	
	private Date timestamp;
	private String message;
	
	public ApiSuccess(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
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
}
