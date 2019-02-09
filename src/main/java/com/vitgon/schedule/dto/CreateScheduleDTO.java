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
public class CreateScheduleDTO implements Serializable {
	private static final long serialVersionUID = 892216499352228418L;
	
	private Group group;
	private String week;
	private int dayNum;
	private int lessonNum;
	
	private Subject subject;
	private String lessonType;
	private User user;
	private String classroom;
}
