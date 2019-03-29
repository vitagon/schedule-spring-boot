package com.vitgon.schedule.service;

import static com.vitgon.schedule.util.LessonUtil.PRACTICE_STR;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.UnfinishedVerificationException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.MessageSource;

import com.vitgon.schedule.dto.ScheduleResponseDto;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.util.LessonUtil;

@RunWith(PowerMockRunner.class)
public class ScheduleResponseServiceTest {
	
	private MessageSource messageSource;
	private UserNameService userNameService;
	private SubjectTitleService subjectTitleService;
	private ScheduleResponseService scheduleResponseService; 
	
	@Before
	public void init() {
		messageSource = mock(MessageSource.class);
		userNameService = mock(UserNameService.class);
		subjectTitleService = mock(SubjectTitleService.class);
		scheduleResponseService = new ScheduleResponseService(messageSource, userNameService, subjectTitleService);
	}

	@PrepareForTest({LessonUtil.class})
	@Test
	public void testCreateResponseObjectMethodWhenUserAndLessonTypeAndClassroomDoNotExist() {
		Schedule schedule = createScheduleWithUserAndLessonTypeAndClassroom();
		Locale locale = new Locale("ru");
		java.util.Locale javaLocale = new java.util.Locale("ru");
		Subject subject = schedule.getSubject();
		User user = schedule.getUser();
		
		when(subjectTitleService.getSubjectTitle(locale, subject)).thenReturn("Math");
		when(userNameService.makeupUsername(user, locale)).thenReturn("James Gosling");
		
		PowerMockito.mockStatic(LessonUtil.class);
		PowerMockito.when(LessonUtil.convertLessonType(schedule.getLessonType())).thenReturn(PRACTICE_STR);
		
		when(messageSource.getMessage(PRACTICE_STR, null, javaLocale)).thenReturn("практика");
		
		ScheduleResponseDto responseDtoObj = scheduleResponseService.createResponseObject(schedule, locale);
		
		Assert.assertEquals(schedule.getId().intValue(), responseDtoObj.getId());
		Assert.assertEquals(subject.getId().intValue(), responseDtoObj.getSubjectId());
		Assert.assertEquals("Math", responseDtoObj.getSubjectTitle());
		Assert.assertEquals(schedule.getDayNum(), responseDtoObj.getDayNum());
		Assert.assertEquals(schedule.getWeek(), responseDtoObj.getWeek());
		Assert.assertEquals(schedule.getLessonNum(), responseDtoObj.getLessonNum());
		Assert.assertEquals(user.getId().intValue(), responseDtoObj.getTeacherId());
		Assert.assertEquals("James Gosling", responseDtoObj.getTeacherName());
		Assert.assertEquals(PRACTICE_STR, responseDtoObj.getLessonType());
		Assert.assertEquals(schedule.getClassroom(), responseDtoObj.getClassroom());
		Assert.assertEquals("практика", responseDtoObj.getLessonTypeName());
		
		verify(subjectTitleService, times(1)).getSubjectTitle(locale, subject);
		verify(userNameService, times(1)).makeupUsername(user, locale);
		
		PowerMockito.verifyStatic(LessonUtil.class, times(2));
		LessonUtil.convertLessonType(schedule.getLessonType());
		
		verify(messageSource, times(1)).getMessage(PRACTICE_STR, null, javaLocale);
	}
	
	@PrepareForTest({LessonUtil.class})
	@Test
	public void testCreateResponseObjectMethodWhenUserAndLessonTypeAndClassroomExist() {
		Schedule schedule = createSchedule();
		Locale locale = new Locale("ru");
		java.util.Locale javaLocale = new java.util.Locale("ru");
		Subject subject = schedule.getSubject();
		User user = schedule.getUser();
		
		when(subjectTitleService.getSubjectTitle(locale, subject)).thenReturn("Math");
		
		ScheduleResponseDto responseDtoObj = scheduleResponseService.createResponseObject(schedule, locale);
		
		Assert.assertEquals(schedule.getId().intValue(), responseDtoObj.getId());
		Assert.assertEquals(subject.getId().intValue(), responseDtoObj.getSubjectId());
		Assert.assertEquals("Math", responseDtoObj.getSubjectTitle());
		Assert.assertEquals(schedule.getDayNum(), responseDtoObj.getDayNum());
		Assert.assertEquals(schedule.getWeek(), responseDtoObj.getWeek());
		Assert.assertEquals(schedule.getLessonNum(), responseDtoObj.getLessonNum());
		Assert.assertEquals(0, responseDtoObj.getTeacherId());
		Assert.assertNull(responseDtoObj.getTeacherName());
		Assert.assertNull(responseDtoObj.getLessonType());
		Assert.assertNull(responseDtoObj.getClassroom());
		Assert.assertNull(responseDtoObj.getLessonTypeName());
		
		// I do not know why, but if I place any this 3 lines after verifyStatic()
		// then will get {@link UnfinishedVerificationException}
		verify(subjectTitleService, times(1)).getSubjectTitle(locale, subject);
		verify(userNameService, Mockito.never()).makeupUsername(user, locale);
		verify(messageSource, Mockito.never()).getMessage(PRACTICE_STR, null, javaLocale);
		
		PowerMockito.verifyStatic(LessonUtil.class, Mockito.never());
		LessonUtil.convertLessonType(schedule.getLessonType());
	}
	
	private Schedule createScheduleWithUserAndLessonTypeAndClassroom() {
		Schedule schedule = new Schedule();
		Subject subject = new Subject();
		User user = new User();
		user.setId(56);
		
		subject.setId(8);
		schedule.setId(1);
		schedule.setSubject(subject);
		schedule.setDayNum(6);
		schedule.setWeek("up");
		schedule.setLessonNum(4);
		schedule.setLessonType(2);
		schedule.setClassroom("G223");
		schedule.setUser(user);
		return schedule;
	}
	
	private Schedule createSchedule() {
		Schedule schedule = new Schedule();
		Subject subject = new Subject();
		
		subject.setId(8);
		schedule.setId(1);
		schedule.setSubject(subject);
		schedule.setDayNum(6);
		schedule.setWeek("up");
		schedule.setLessonNum(4);
		return schedule;
	}
}
