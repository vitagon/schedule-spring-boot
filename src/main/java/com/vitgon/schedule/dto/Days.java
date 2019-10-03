package com.vitgon.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Days {
	
	@JsonProperty("monday")
	MONDAY(1),
	@JsonProperty("tuesday")
	TUESDAY(2),
	@JsonProperty("wednesday")
	WEDNESDAY(3),
	@JsonProperty("thursday")
	THURSDAY(4),
	@JsonProperty("friday")
	FRIDAY(5),
	@JsonProperty("saturday")
	SATURDAY(6),
	@JsonProperty("sunday")
	SUNDAY(7);
	
	private Integer dayNum;
	
	Days(Integer dayNum) {
		this.dayNum = dayNum;
	}
	
	public Integer getDayNum() {
		return this.dayNum;
	}
}
