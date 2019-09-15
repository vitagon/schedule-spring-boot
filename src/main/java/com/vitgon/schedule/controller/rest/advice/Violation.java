package com.vitgon.schedule.controller.rest.advice;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


public class Violation {
	private final String fieldName;
	private List<String> messages;

	public Violation(String fieldName, List<String> messages) {
		this.fieldName = fieldName;
		this.messages = messages;
	}

	public String getFieldName() {
		return fieldName;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
