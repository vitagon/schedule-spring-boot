package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.List;

import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.auth.User;

public class ScheduleDtoServiceTest {
	
//	private SubjectTranslationService subjectTranslationService;
//	private UserNameService userNameService;
//	private ScheduleTreeService scheduleTreeService;
//
//	@Before
//	public void init() {
//		subjectTranslationService = mock(SubjectTranslationService.class);
//		userNameService = mock(UserNameService.class);
//		scheduleTreeService = new ScheduleTreeService(subjectTranslationService, userNameService);
//	}
	
//	@Test
//	public void testGetScheduleTreeMethodWhenUserIsNotNull() {
//		List<ScheduleProjection> schedules = createSchedules();
//		Locale locale = new Locale("en");
//		Subject subject = schedules.get(0).getSubject();
//		User user = schedules.get(0).getUser();
//		
//		when(subjectTranslationService.getSubjectTitle(subject, locale, true)).thenReturn("Math");
//		when(userNameService.makeupUsername(user, locale)).thenReturn("James Gosling");
//		
//		ScheduleTree scheduleTree = scheduleTreeService.getScheduleTree(schedules, locale);
//		ScheduleDto scheduleDto = scheduleTree.get("tuesday", 3, "up");
//		Assert.assertEquals("Math", scheduleDto.getSubjectTitle());
//		Assert.assertEquals("James", scheduleDto.getTeacher().getFirstname());
//		
//		verify(subjectTranslationService, times(1)).getSubjectTitle(subject, locale, true);
//		verify(userNameService, times(1)).makeupUsername(user, locale);
//	}
//	
//	@Test
//	public void testGetScheduleTreeMethodWhenUserIsNull() {
//		List<Schedule> schedules = createSchedulesWithoutTeacher();
//		Locale locale = new Locale("en");
//		Subject subject = schedules.get(0).getSubject();
//		User user = null;
//		
//		when(subjectTranslationService.getSubjectTitle(subject, locale, true)).thenReturn("Math");
//		
//		ScheduleTree scheduleTree = scheduleTreeService.getScheduleTree(schedules, locale);
//		ScheduleDto scheduleDto = scheduleTree.get("tuesday", 3, "up");
//		Assert.assertEquals("Math", scheduleDto.getSubjectTitle());
//		Assert.assertNull(scheduleDto.getTeacher());
//		
//		verify(subjectTranslationService, times(1)).getSubjectTitle(subject, locale, true);
//		verify(userNameService, Mockito.never()).makeupUsername(user, locale);
//	}
	
	private List<Schedule> createSchedules() {
		List<Schedule> schedules = new ArrayList<>();
		
		Schedule schedule = new Schedule();
		schedule.setId(65);
		schedule.setDayNum(2);
		schedule.setLessonNum(3);
		schedule.setWeek("up");
		
		Subject subject = new Subject();
		subject.setId(34);
		schedule.setSubject(subject);
		
		schedule.setLessonType(2);
		schedule.setClassroom("G231");
		
		User user = new User();
		user.setId(115);
		user.setKeyFirstname("James");
		user.setKeyLastname("Gosling");
		schedule.setUser(user);
		
		schedules.add(schedule);
		return schedules;
	}
	
	private List<Schedule> createSchedulesWithoutTeacher() {
		List<Schedule> schedules = new ArrayList<>();
		
		Schedule schedule = new Schedule();
		schedule.setId(65);
		schedule.setDayNum(2);
		schedule.setLessonNum(3);
		schedule.setWeek("up");
		
		Subject subject = new Subject();
		subject.setId(34);
		schedule.setSubject(subject);
		
		schedule.setLessonType(2);
		schedule.setClassroom("G231");
		
		schedules.add(schedule);
		return schedules;
	}
}
