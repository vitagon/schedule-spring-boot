package com.vitgon.schedule.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SchedulePOJO {
	private int scheduleId;
	private int subjId;
	private String subjectTitle;
	private String lessonType;
	private String classroom;
	private TeacherPOJO teacher;
}
