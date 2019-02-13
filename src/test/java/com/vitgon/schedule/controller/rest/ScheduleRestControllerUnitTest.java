package com.vitgon.schedule.controller.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.dto.ScheduleResponseDTO;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.ScheduleResponseService;
import com.vitgon.schedule.service.database.ScheduleService;
import com.vitgon.schedule.util.LessonUtil;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleRestControllerUnitTest {
	
	@Mock
	private ScheduleService scheduleService;
	@Mock
	private LocaleConverterService localeConverterService;
	@Mock
	private ScheduleResponseService scheduleResponseService;
	
	@InjectMocks
	private ScheduleRestController scheduleRestController;
	
	@Test
	public void testCreateMethod() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		Locale locale = new Locale("en");
		Schedule scheduleTransient = createScheduleTransient();
		
		given(localeConverterService.getClientLocale(request)).willReturn(locale);
		given(scheduleService.findByGroupAndDayNumAndWeekAndLessonNum(
				scheduleTransient.getGroup(),
				scheduleTransient.getDayNum(),
				scheduleTransient.getWeek(),
				scheduleTransient.getLessonNum()
		)).willReturn(null);
		
		Schedule scheduleWithId = scheduleTransient.clone();
		scheduleWithId.setId(5);
		
		// must return schedule with generated id
		given(scheduleService.save(scheduleTransient)).willReturn(scheduleWithId);
		
		ScheduleResponseDTO mockResponse = createScheduleResponse(scheduleWithId);
		given(scheduleResponseService.createResponseObject(scheduleWithId, locale)).willReturn(mockResponse);
		
		// call method on controller
		ScheduleResponseDTO realResponse = scheduleRestController.create(scheduleTransient, request);
		assertThat(realResponse, is(mockResponse));
		
		assertThat(realResponse.getSubjectId(), is(scheduleTransient.getSubject().getId()));
		assertThat(realResponse.getTeacherId(), is(scheduleTransient.getUser().getId()));
		assertThat(realResponse.getWeek(), is(scheduleTransient.getWeek()));
		assertThat(realResponse.getDayNum(), is(scheduleTransient.getDayNum()));
		assertThat(realResponse.getLessonNum(), is(scheduleTransient.getLessonNum()));
		assertThat(realResponse.getLessonType(), is(LessonUtil.convertLessonType(scheduleTransient.getLessonType())));
		assertThat(realResponse.getClassroom(), is(scheduleTransient.getClassroom()));
	}
	
	@Test
	public void testEditMethod() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		Locale locale = new Locale("en");
		Schedule oldSchedule = createScheduleTransient();
		oldSchedule.setId(5);
		
		Schedule newSchedule = updateSchedule(oldSchedule.clone());
		
		given(localeConverterService.getClientLocale(request)).willReturn(locale);
		// here we return clone of oldSchedule because
		// in controller this object will be changed
		// but we want to keep it unmodified for further checking 
		given(scheduleService.findByGroupAndDayNumAndWeekAndLessonNum(
				newSchedule.getGroup(),
				newSchedule.getDayNum(),
				newSchedule.getWeek(),
				newSchedule.getLessonNum()
		)).willReturn(oldSchedule.clone());
		
		// in this case of updating the returning object will be the same
		given(scheduleService.update(newSchedule)).willReturn(newSchedule);
		
		ScheduleResponseDTO mockResponse = createScheduleResponse(newSchedule);
		given(scheduleResponseService.createResponseObject(newSchedule, locale)).willReturn(mockResponse);
		
		// call method on controller
		ScheduleResponseDTO realResponse = scheduleRestController.update(newSchedule, request);
		assertThat(realResponse, is(mockResponse));
		
		assertThat(realResponse.getSubjectId(), is(newSchedule.getSubject().getId()));
		assertThat(realResponse.getLessonType(), is(LessonUtil.convertLessonType(newSchedule.getLessonType())));
		assertThat(realResponse.getTeacherId(), is(newSchedule.getUser().getId()));
		assertThat(realResponse.getClassroom(), is(newSchedule.getClassroom()));
		
		assertThat(realResponse.getSubjectId(), is(not(oldSchedule.getSubject().getId())));
		assertThat(realResponse.getLessonType(), is(not(LessonUtil.convertLessonType(oldSchedule.getLessonType()))));
		assertThat(realResponse.getTeacherId(), is(not(oldSchedule.getUser().getId())));
		assertThat(realResponse.getClassroom(), is(not(oldSchedule.getClassroom())));
	}
	
	private static Schedule createScheduleTransient() {
		Schedule schedule = new Schedule();
		Subject subject = new Subject();
		subject.setId(52);
		User user = new User();
		user.setId(3);
		
		schedule.setGroup(new Group());
		schedule.setWeek("up");
		schedule.setDayNum(4);
		schedule.setSubject(subject);
		schedule.setLessonNum(2);
		schedule.setLessonType(LessonUtil.LECTURE);
		schedule.setUser(user);
		schedule.setClassroom("H2352");
		return schedule;
	}
	
	private static Schedule updateSchedule(Schedule schedule) {
		Subject subject = new Subject();
		subject.setId(8);
		User user = new User();
		user.setId(6);
		
		schedule.setSubject(subject);
		schedule.setLessonType(LessonUtil.PRACTICE);
		schedule.setUser(user);
		schedule.setClassroom("A642");
		return schedule;
	}
	
	private static ScheduleResponseDTO createScheduleResponse(Schedule schedule) {
		ScheduleResponseDTO response = new ScheduleResponseDTO();
		response.setId(schedule.getId());
		response.setSubjectId(schedule.getSubject().getId());
		response.setDayNum(schedule.getDayNum());
		response.setWeek(schedule.getWeek());
		response.setLessonNum(schedule.getLessonNum());
		
		response.setTeacherId(schedule.getUser().getId());
		response.setLessonType(LessonUtil.convertLessonType(schedule.getLessonType()));
		response.setClassroom(schedule.getClassroom());
		return response;
	}
}
