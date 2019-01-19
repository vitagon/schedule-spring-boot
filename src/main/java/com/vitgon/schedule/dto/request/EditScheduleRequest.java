package com.vitgon.schedule.dto.request;

import lombok.Data;

@Data
public class EditScheduleRequest {
	private int groupId;
	private int scheduleId;
	private int week;
	private int dayNum;
	private int lessonNum;
	
	private int subjectId;
	private int lessonType;
	private int userId;
	private String classroom;
}
