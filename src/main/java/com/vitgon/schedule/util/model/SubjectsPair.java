package com.vitgon.schedule.util.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectsPair {
	private Map<String, String> up;
	private Map<String, String> down;
}
