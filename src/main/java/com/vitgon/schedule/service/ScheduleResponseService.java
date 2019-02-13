package com.vitgon.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.ScheduleResponseDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.util.LessonUtil;

@Service
public class ScheduleResponseService {
	
	private MessageSource messageSource;
	private UserNameService userNameService;
	private SubjectTitleService subjectTitleService;

	@Autowired
	public ScheduleResponseService(MessageSource messageSource,
								   UserNameService userNameService,
								   SubjectTitleService subjectTitleService) {
		this.messageSource = messageSource;
		this.userNameService = userNameService;
		this.subjectTitleService = subjectTitleService;
	}

	public ScheduleResponseDTO createResponseObject(Schedule schedule, Locale locale) {
		ScheduleResponseDTO response = new ScheduleResponseDTO();
		response.setId(schedule.getId());
		response.setSubjectId(schedule.getSubject().getId());
		response.setSubjectTitle(subjectTitleService.getSubjectTitle(locale, schedule.getSubject()));
		response.setDayNum(schedule.getDayNum());
		response.setWeek(schedule.getWeek());
		response.setLessonNum(schedule.getLessonNum());
		
		User user = schedule.getUser();
		
		if (user != null) {
			response.setTeacherId(user.getId());
			response.setTeacherName(userNameService.makeupUsername(user, locale));
		}
		
		if (schedule.getLessonType() != 0) {
			response.setLessonType(LessonUtil.convertLessonType(schedule.getLessonType()));
		}
		
		if (schedule.getClassroom() != null) {
			response.setClassroom(schedule.getClassroom());
		}
		
		if (schedule.getLessonType() != 0) {
			java.util.Locale javaLocale = new java.util.Locale(locale.getCode());
			response.setLessonTypeName(messageSource.getMessage(LessonUtil.convertLessonType(schedule.getLessonType()), null, javaLocale));
		}
		
		return response;
	}
}
