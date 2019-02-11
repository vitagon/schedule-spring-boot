package com.vitgon.schedule.dto;

import java.io.Serializable;

import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.auth.User;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ScheduleDTO implements Serializable {
	private static final long serialVersionUID = 892216499352228418L;
	
	private int groupId;
	private String week;
	private int dayNum;
	private int lessonNum;
	
	private int subjectId;
	private String lessonType;
	private int userId;
	private String classroom;
}
