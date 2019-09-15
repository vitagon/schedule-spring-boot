package com.vitgon.schedule.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EditGroupDto {

	public EditGroupDto() {
	}

	public EditGroupDto(int id, int courseNum) {
		this.id = id;
		this.courseNum = courseNum;
	}

	@Min(value = 1, message = "{NotEmpty.default}")
	private int id;
	
	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 7, message = "{NotEmpty.default}")
	private int courseNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
}
