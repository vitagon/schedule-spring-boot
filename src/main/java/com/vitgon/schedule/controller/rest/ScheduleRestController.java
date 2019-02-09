package com.vitgon.schedule.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vitgon.schedule.dto.CreateScheduleDTO;
import com.vitgon.schedule.dto.EditScheduleDTO;
import com.vitgon.schedule.dto.EditScheduleResponseDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.service.EditScheduleResponseService;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.database.ScheduleService;
import com.vitgon.schedule.util.LessonUtil;

@Controller
public class ScheduleRestController {
	
	private ScheduleService scheduleService;
	private LocaleConverterService localeConverterService;
	private EditScheduleResponseService editScheduleResponseService;
	
	@Autowired
	public ScheduleRestController(ScheduleService scheduleService,
								  LocaleConverterService localeConverterService,
								  EditScheduleResponseService editScheduleResponseService) {
		this.scheduleService = scheduleService;
		this.localeConverterService = localeConverterService;
		this.editScheduleResponseService = editScheduleResponseService;
	}
	
	/**
	 * Attributes for creating new schedule: groupId, week, dayNum, lessonNum
	 * 
	 * @param editScheduleReq
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/api/schedule/create",
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	private EditScheduleResponseDTO create(@RequestBody CreateScheduleDTO createScheduleDTO, HttpServletRequest request) {
		Locale locale = localeConverterService.getClientLocale(request);
		
		Schedule schedule = new Schedule();
		schedule.setGroup(createScheduleDTO.getGroup());
		schedule.setWeek(createScheduleDTO.getWeek());
		schedule.setDayNum(createScheduleDTO.getDayNum());
		schedule.setSubject(createScheduleDTO.getSubject());
		schedule.setLessonNum(createScheduleDTO.getLessonNum());
		schedule.setLessonType(LessonUtil.convertLessonType(createScheduleDTO.getLessonType()));
		schedule.setUser(createScheduleDTO.getUser());
		schedule.setClassroom(createScheduleDTO.getClassroom());
		
		schedule = scheduleService.save(schedule);
		
		return editScheduleResponseService.createResponseObject(schedule, locale);
	}
	
	/**
	 * Attributes that we can change: subject, lessonType, teacher (user), classroom
	 * 
	 * @param editScheduleReq
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/api/schedule/edit",
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	private EditScheduleResponseDTO update(@RequestBody EditScheduleDTO editScheduleDTO, HttpServletRequest request) {
		Locale locale = localeConverterService.getClientLocale(request);
		
		Schedule schedule = scheduleService.findById(editScheduleDTO.getScheduleId());
		if (schedule == null) {
			throw new IllegalArgumentException("Schedule was not found!");
		}
		schedule.setSubject(editScheduleDTO.getSubject());
		schedule.setLessonType(LessonUtil.convertLessonType(editScheduleDTO.getLessonType()));
		schedule.setUser(editScheduleDTO.getUser());
		schedule.setClassroom(editScheduleDTO.getClassroom());
		
		schedule = scheduleService.update(schedule);
		
		return editScheduleResponseService.createResponseObject(schedule, locale);
	}
	
	
}
