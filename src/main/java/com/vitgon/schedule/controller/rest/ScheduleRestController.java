package com.vitgon.schedule.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vitgon.schedule.dto.EditScheduleDTO;
import com.vitgon.schedule.dto.ResponseDTO;
import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.GroupService;
import com.vitgon.schedule.service.ScheduleService;
import com.vitgon.schedule.service.SubjectService;
import com.vitgon.schedule.service.UserService;

@Controller
public class ScheduleRestController {
	
	private ScheduleService scheduleService;
	private SubjectService subjectService;
	private UserService userService;
	private GroupService groupService;

	@Autowired
	public ScheduleRestController(ScheduleService scheduleService,
								  SubjectService subjectService,
								  UserService userService,
								  GroupService groupService) {
		this.scheduleService = scheduleService;
		this.subjectService = subjectService;
		this.userService = userService;
		this.groupService = groupService;
	}



	@ResponseBody
	@PostMapping("/api/schedule/edit")
	public ResponseDTO editSchedulePage(@ModelAttribute EditScheduleDTO editScheduleReq) {
		Schedule schedule = null;
		User user = null;
		String message;
		
		// attribute for editing schedule
		int scheduleId = editScheduleReq.getScheduleId();
		
		// attributes for creating new schedule
		int groupId = editScheduleReq.getGroupId();
		int week = editScheduleReq.getWeek();
		int dayNum = editScheduleReq.getDayNum();
		int lessonNum = editScheduleReq.getLessonNum();
		
		// attributes that we can change
		int subjectId = editScheduleReq.getSubjectId();
		int lessonType = editScheduleReq.getLessonType();
		int userId = editScheduleReq.getUserId();
		String classroom = editScheduleReq.getClassroom();
		
		Subject subject = subjectService.findById(subjectId);
		if (userId != 0) {
			user = userService.findById(userId);
		}
		
		// if scheduleId not equals to 0 then change attributes
		// otherwise create new schedule with provided attributes
		if (scheduleId != 0) {
			schedule = scheduleService.findById(scheduleId);
			schedule.setSubject(subject);
			schedule.setLessonType(lessonType);
			schedule.setUser(user);
			schedule.setClassroom(classroom);
			scheduleService.update(schedule);
			message = "schedule was updated";
		} else {
			Group group = groupService.findById(groupId);
			
			schedule = new Schedule();
			schedule.setGroup(group);
			schedule.setWeek(week);
			schedule.setDayNum(dayNum);
			schedule.setSubject(subject);
			schedule.setLessonNum(lessonNum);
			schedule.setLessonType(lessonType);
			schedule.setUser(user);
			schedule.setClassroom(classroom);
			scheduleService.save(schedule);
			
			message = "schedule was created";
		}
		
		return new ResponseDTO(true, message);
	}
}
