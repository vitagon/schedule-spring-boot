package com.vitgon.schedule.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.EnumMatch;
import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.SchoolExists;
import com.vitgon.schedule.annotation.validation.UniqueMajor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class EditMajorDto {

	public EditMajorDto() {
	}

	public EditMajorDto(int id, String name, int duration, String degree, Integer schoolId) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.degree = degree;
		this.schoolId = schoolId;
	}

	@Min(value = 1, message = "{NotEmpty.default}")
	protected int id;

	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	protected String name;

	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 6, message = "{Max.default}")
	protected int duration;
	
	@NotNull(message = "{NotNull.default}")
	@EnumMatch(enumClazz = DegreeEnum.class, message = "{Degree.noMatch}")
	protected String degree;

	@SchoolExists
	protected Integer schoolId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
}
