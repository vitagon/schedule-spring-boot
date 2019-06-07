package com.vitgon.schedule.controller.rest.advice;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {
	private final String fieldName;
	private List<String> messages;
}
