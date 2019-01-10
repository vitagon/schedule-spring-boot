package com.vitgon.schedule.model.request;

import lombok.Data;

@Data
public class EditScheduleRequest {
	private String subjectTitle;
	private String lessonNum;
	private String lessonType;
	private String teacherId;
	private String classroom;
}
