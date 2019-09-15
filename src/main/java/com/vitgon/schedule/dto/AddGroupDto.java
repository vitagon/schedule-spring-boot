package com.vitgon.schedule.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AddGroupDto {

	public AddGroupDto() {
	}

	public AddGroupDto(int majorId, int number, String suffix, int courseNum) {
		this.majorId = majorId;
		this.number = number;
		this.suffix = suffix;
		this.courseNum = courseNum;
	}

	@Min(value = 1, message = "{NotEmpty.default}")
	private int majorId;
	
	@Min(value = 1000, message = "{NotEmpty.default}")
	@Max(value = 9999, message = "{NotEmpty.default}")
	private int number;
	
	@NotEmpty(message = "{NotEmpty.default}")
	private String suffix;
	
	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 7, message = "{NotEmpty.default}")
	private int courseNum;

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
}
