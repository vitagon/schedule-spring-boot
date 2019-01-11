package com.vitgon.schedule.model.request;

import lombok.Data;

@Data
public class EditScheduleRequest {
	private int scheduleId;
	private String subjectTitle;
	private String lessonType;
	private String teacherId;
	private String classroom;
}
