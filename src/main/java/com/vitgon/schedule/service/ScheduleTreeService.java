package com.vitgon.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.collection.ScheduleTree;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.pojo.SchedulePOJO;
import com.vitgon.schedule.pojo.TeacherPOJO;
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
			
			SchedulePOJO schedulePOJO = new SchedulePOJO();
			schedulePOJO.setScheduleId(schedule.getId());
			schedulePOJO.setSubjId(schedule.getSubject().getId());
			schedulePOJO.setSubjectTitle(subjectTranslationService.getSubjectTitle(schedule.getSubject(), locale));
			schedulePOJO.setLessonType(LessonUtil.convertLessonType(schedule.getLessonType()));
			
			if (schedule.getUser() != null) {
				schedulePOJO.setTeacher(new TeacherPOJO(
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
