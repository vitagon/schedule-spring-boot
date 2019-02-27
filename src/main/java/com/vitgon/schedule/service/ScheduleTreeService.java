package com.vitgon.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.collection.ScheduleTree;
import com.vitgon.schedule.dto.ScheduleDto;
import com.vitgon.schedule.dto.TeacherDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;
import com.vitgon.schedule.util.LessonUtil;
import com.vitgon.schedule.util.ScheduleUtil;

@Component
public class ScheduleTreeService {

	private SubjectTranslationService subjectTranslationService;
	private UserNameService userNameService;
	
	@Autowired
	public ScheduleTreeService(SubjectTranslationService subjectTranslationService, UserNameService userNameService) {
		this.subjectTranslationService = subjectTranslationService;
		this.userNameService = userNameService;
	}
	
	public ScheduleTree getScheduleTree(List<Schedule> schedules, Locale locale) {
		ScheduleTree scheduleTree = new ScheduleTree();
		for (Schedule schedule : schedules) {
			String dayTitle = ScheduleUtil.DAYS_MAP.get(schedule.getDayNum());
			int lessonNum = schedule.getLessonNum();
			String weekType = schedule.getWeek();
			
			ScheduleDto schedulePOJO = new ScheduleDto();
			schedulePOJO.setScheduleId(schedule.getId());
			schedulePOJO.setSubjId(schedule.getSubject().getId());
			schedulePOJO.setSubjectTitle(subjectTranslationService.getSubjectTitle(schedule.getSubject(), locale, true));
			schedulePOJO.setLessonType(LessonUtil.convertLessonType(schedule.getLessonType()));
			
			if (schedule.getUser() != null) {
				schedulePOJO.setTeacher(new TeacherDto(
						schedule.getUser().getId(),
						userNameService.makeupUsername(schedule.getUser(), locale)
				));
			}
			
			schedulePOJO.setClassroom(schedule.getClassroom());
			scheduleTree.add(dayTitle, lessonNum, weekType, schedulePOJO);
		}
		return scheduleTree;
	}
}
