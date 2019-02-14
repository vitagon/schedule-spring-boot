package com.vitgon.schedule.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiSuccess {
	private Date timestamp;
	private String message;
}
