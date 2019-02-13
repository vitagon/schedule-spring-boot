package com.vitgon.schedule.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vitgon.schedule.annotation.FromDTO;
import com.vitgon.schedule.dto.ScheduleDTO;
import com.vitgon.schedule.dto.ScheduleResponseDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.ScheduleResponseService;
import com.vitgon.schedule.service.database.ScheduleService;

@Controller
public class ScheduleRestController {
	
	private ScheduleService scheduleService;
	private LocaleConverterService localeConverterService;
	private ScheduleResponseService scheduleResponseService;
	
	@Autowired
	public ScheduleRestController(ScheduleService scheduleService,
								  LocaleConverterService localeConverterService,
								  ScheduleResponseService scheduleResponseService) {
		this.scheduleService = scheduleService;
		this.localeConverterService = localeConverterService;
		this.scheduleResponseService = scheduleResponseService;
	}
	
	/**
	 * Method creates new schedule using groupId, dayNum, weekType, lessonNum 
	 * 
	 * @param scheduleTransient ScheduleDTO that mapped to Schedule
	 * @param request
	 * @return ScheduleResponseDTO
	 */
	@ResponseBody
	@PostMapping(value = "/api/schedule/create",
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ScheduleResponseDTO create(
			@FromDTO(ScheduleDTO.class) Schedule scheduleTransient,
			HttpServletRequest request) {
		Locale locale = localeConverterService.getClientLocale(request);
		
		Schedule schedule = scheduleService.findByGroupAndDayNumAndWeekAndLessonNum(
				scheduleTransient.getGroup(),
				scheduleTransient.getDayNum(),
				scheduleTransient.getWeek(),
				scheduleTransient.getLessonNum()
		);
		if (schedule != null) {
			throw new IllegalArgumentException(String.format(
					"Such schedule already exists with id=%d !",
					schedule.getId()
			));
		}
		
		schedule = new Schedule();
		schedule.setGroup(scheduleTransient.getGroup());
		schedule.setWeek(scheduleTransient.getWeek());
		schedule.setDayNum(scheduleTransient.getDayNum());
		schedule.setSubject(scheduleTransient.getSubject());
		schedule.setLessonNum(scheduleTransient.getLessonNum());
		schedule.setLessonType(scheduleTransient.getLessonType());
		schedule.setUser(scheduleTransient.getUser());
		schedule.setClassroom(scheduleTransient.getClassroom());
		
		schedule = scheduleService.save(schedule);
		
		return scheduleResponseService.createResponseObject(schedule, locale);
	}
	
	
	/**
	 * Method changes schedule. It uses [groupId, dayNum, weekType, lessonNum]
	 * to find schedule in the database 
	 * 
	 * @param scheduleTransient
	 * @param request
	 * @return ScheduleResponseDTO
	 */
	@ResponseBody
	@PostMapping(value = "/api/schedule/edit",
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ScheduleResponseDTO update(
			@FromDTO(ScheduleDTO.class) Schedule scheduleTransient,
			HttpServletRequest request) {
		Locale locale = localeConverterService.getClientLocale(request);
		
		Schedule schedule = scheduleService.findByGroupAndDayNumAndWeekAndLessonNum(
				scheduleTransient.getGroup(),
				scheduleTransient.getDayNum(),
				scheduleTransient.getWeek(),
				scheduleTransient.getLessonNum()
		);
		if (schedule == null) {
			throw new IllegalArgumentException("Such schedule was not found!");
		}
		schedule.setSubject(scheduleTransient.getSubject());
		schedule.setLessonType(scheduleTransient.getLessonType());
		schedule.setUser(scheduleTransient.getUser());
		schedule.setClassroom(scheduleTransient.getClassroom());
		
		schedule = scheduleService.update(schedule);
		
		return scheduleResponseService.createResponseObject(schedule, locale);
	}
	
	
}
