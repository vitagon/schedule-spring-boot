package com.vitgon.schedule.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDto {
	private int scheduleId;
	private int subjId;
	private String subjectTitle;
	private String lessonType;
	private String classroom;
	private TeacherDto teacher;
}
