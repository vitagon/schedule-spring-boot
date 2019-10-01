package com.vitgon.schedule.controller.rest;

import com.vitgon.schedule.dto.EditScheduleDto;
import com.vitgon.schedule.dto.ScheduleDto;
import com.vitgon.schedule.dto.ScheduleDto.WeekSchedule.LessonSchedule;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.projection.ScheduleProjection;
import com.vitgon.schedule.service.ScheduleDtoService;
import com.vitgon.schedule.service.database.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleRestController {
	
	private ScheduleService scheduleService;
	private ScheduleDtoService scheduleDtoService;
	
	@Autowired
	public ScheduleRestController(ScheduleService scheduleService, ScheduleDtoService scheduleDtoService) {
		super();
		this.scheduleService = scheduleService;
		this.scheduleDtoService = scheduleDtoService;
	}
	
	@DeleteMapping("/{scheduleId}")
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess deleteSchedule(@PathVariable @Min(1) Integer scheduleId) {
		scheduleService.deleteById(scheduleId);
		return new ApiSuccess(new Date(), "Record was successfully deleted!");
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LessonSchedule createSchedule(@RequestBody @Valid EditScheduleDto editScheduleDto) {
		
		ScheduleProjection scheduleProjection = scheduleService.findByGroupIdAndDayNumAndWeekAndLessonNum(
				editScheduleDto.getGroupId(),
				editScheduleDto.getDay().getDayNum(),
				editScheduleDto.getWeek(),
				editScheduleDto.getLessonNum()
		);
		if (scheduleProjection != null) {
			throw new IllegalArgumentException(String.format(
					"Such schedule already exists with id=%d !",
					scheduleProjection.getId()
			));
		}
		
		Schedule schedule = scheduleDtoService.editScheduleDtoToSchedule(editScheduleDto);
		schedule = scheduleService.save(schedule);
		return scheduleDtoService.getLessonScheduleByScheduleId(schedule.getId());
	}
	
	@PutMapping("/{scheduleId}")
	@ResponseStatus(HttpStatus.OK)
	public LessonSchedule updateSchedule(@RequestBody @Valid EditScheduleDto editScheduleDto) {
		ScheduleProjection scheduleProjection = scheduleService.findByGroupIdAndDayNumAndWeekAndLessonNum(
				editScheduleDto.getGroupId(),
				editScheduleDto.getDay().getDayNum(),
				editScheduleDto.getWeek(),
				editScheduleDto.getLessonNum()
		);
		if (scheduleProjection == null) {
			throw new IllegalArgumentException("Such schedule was not found!");
		}
		Schedule schedule = scheduleDtoService.editScheduleDtoToSchedule(editScheduleDto);
		schedule.setId(scheduleProjection.getId());
		schedule = scheduleService.update(schedule);
		return scheduleDtoService.getLessonScheduleByScheduleId(schedule.getId());
	}
	
	@GetMapping("/group-id/{groupId}")
	public ScheduleDto getScheduleList(@PathVariable("groupId") Integer groupId) {
		return scheduleDtoService.getScheduleDtoByGroupId(groupId);
	}
}
