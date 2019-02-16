package com.vitgon.schedule.controller.rest;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.annotation.FromDTO;
import com.vitgon.schedule.dto.ScheduleDTO;
import com.vitgon.schedule.dto.ScheduleResponseDTO;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.ScheduleResponseService;
import com.vitgon.schedule.service.database.ScheduleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
public class ScheduleRestController {
	
	private ScheduleService scheduleService;
	private LocaleConverterService localeConverterService;
	private ScheduleResponseService scheduleResponseService;
	
	/**
	 * Delete schedule record by its id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(params = {"id"})
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess delete(@RequestParam("id") Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("id can not be null!");
		}
		scheduleService.deleteById(id);
		return new ApiSuccess(new Date(), "Record was successfully deleted!");
	}
	
	/**
	 * Method creates new schedule using groupId, dayNum, weekType, lessonNum 
	 * 
	 * @param scheduleTransient ScheduleDTO that mapped to Schedule
	 * @param request
	 * @return ScheduleResponseDTO
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
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
		
		scheduleTransient = scheduleService.save(scheduleTransient);
		return scheduleResponseService.createResponseObject(scheduleTransient, locale);
	}
	
	
	/**
	 * Method changes schedule. It uses [groupId, dayNum, weekType, lessonNum]
	 * to find schedule in the database 
	 * 
	 * @param scheduleTransient
	 * @param request
	 * @return ScheduleResponseDTO
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
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
