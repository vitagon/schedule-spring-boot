package com.vitgon.schedule.projection;

public interface ScheduleProjection {
	Integer getGroup_id();
	
	Integer getId();
	Integer getDay_num();
	String getWeek_type();
	Integer getLesson_num();
	String getLesson_time();
	String getLesson_type_name();
	Integer getLesson_type_id();
	String getClassroom();
	
	Integer getSubject_id();
	String getSubject_name();
	
	Integer getUser_id();
	String getLastname();
	String getFirstname();
	String getMiddlename();
}
