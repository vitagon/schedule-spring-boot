package com.vitgon.schedule.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vitgon.schedule.serialize.StringToDaysEnumDeserializer;

import lombok.Data;
import lombok.NoArgsConstructor;


public class EditScheduleDto implements Serializable {
	private static final long serialVersionUID = 892216499352228418L;

	public EditScheduleDto() {
	}

	public EditScheduleDto(int groupId, String week, Days day, int lessonNum, Integer subjectId, Integer lessonTypeId, int userId, String classroom) {
		this.groupId = groupId;
		this.week = week;
		this.day = day;
		this.lessonNum = lessonNum;
		this.subjectId = subjectId;
		this.lessonTypeId = lessonTypeId;
		this.userId = userId;
		this.classroom = classroom;
	}

	@Min(value = 1, message = "{NotNull.default}")
	private int groupId;
	
	@NotEmpty(message = "{NotEmpty.default}")
	@Pattern(regexp = "^up|down$")
	private String week;
	
	@NotNull(message = "{NotNull.default}")
	@JsonDeserialize(using = StringToDaysEnumDeserializer.class)
	private Days day;
	
	@Min(value = 1, message = "{NotNull.default}")
	private int lessonNum;
	
	
	
	@Min(value = 1, message = "{NotNull.default}")
	private Integer subjectId;
	
	@PositiveOrZero
	private Integer lessonTypeId;
	
	@PositiveOrZero
	private int userId;
	
	@Pattern(regexp = "^([A-Z]{1}[0-9]{3,4})?$")
	private String classroom;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Days getDay() {
		return day;
	}

	public void setDay(Days day) {
		this.day = day;
	}

	public int getLessonNum() {
		return lessonNum;
	}

	public void setLessonNum(int lessonNum) {
		this.lessonNum = lessonNum;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getLessonTypeId() {
		return lessonTypeId;
	}

	public void setLessonTypeId(Integer lessonTypeId) {
		this.lessonTypeId = lessonTypeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
}
