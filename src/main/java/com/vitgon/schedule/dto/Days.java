package com.vitgon.schedule.dto;

public enum Days {
	
	MONDAY(1),
	TUESDAY(2),
	WEDNESDAY(3),
	THURSDAY(4),
	FRIDAY(5),
	SATURDAY(6),
	SUNDAY(7);
	
	private Integer dayNum;
	
	Days(Integer dayNum) {
		this.dayNum = dayNum;
	}
	
	public Integer getDayNum() {
		return this.dayNum;
	}
}
