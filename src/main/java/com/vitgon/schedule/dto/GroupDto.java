package com.vitgon.schedule.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vitgon.schedule.annotation.validation.MajorExists;

public class GroupDto {
	private Integer id;
	
	@NotEmpty(message = "{NotEmpty.default}")
	private String name;
	private String translation;
	
	@NotNull(message = "{NotEmpty.default}")
	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 6, message = "{NotEmpty.default}")
	private Integer courseNum;
	
	@MajorExists
	private Integer majorId;
	
	public GroupDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

    public GroupDto() {
    }

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}

	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
}
