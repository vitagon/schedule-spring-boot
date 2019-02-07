package com.vitgon.schedule.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EditScheduleDTO implements Serializable {
	private static final long serialVersionUID = 892216499352228418L;
	
	private int groupId;
	private int scheduleId;
	private String week;
	private int dayNum;
	private int lessonNum;
	
	private int subjectId;
	private String lessonType;
	private int userId;
	private String classroom;
}
