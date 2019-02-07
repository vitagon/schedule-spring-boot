package com.vitgon.schedule.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vitgon.schedule.dto.EditScheduleDTO;
import com.vitgon.schedule.dto.EditScheduleResponseDTO;
import com.vitgon.schedule.model.Group;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.UserNameService;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.ScheduleService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;
import com.vitgon.schedule.util.LessonUtil;

@Controller
public class ScheduleRestController {
	
	private ScheduleService scheduleService;
	private SubjectService subjectService;
	private UserService userService;
	private GroupService groupService;
	
	private LocaleConverterService localeConverterService;
	private SubjectTranslationService subjectTranslationService;
	
	private MessageSource messageSource;
	private UserNameService userNameService;

	@Autowired
	public ScheduleRestController(ScheduleService scheduleService,
								  SubjectService subjectService,
								  UserService userService,
								  GroupService groupService,
								  LocaleConverterService localeConverterService,
								  SubjectTranslationService subjectTranslationService,
								  MessageSource messageSource,
								  UserNameService userNameService) {
		this.scheduleService = scheduleService;
		this.subjectService = subjectService;
		this.userService = userService;
		this.groupService = groupService;
		this.localeConverterService = localeConverterService;
		this.subjectTranslationService = subjectTranslationService;
		this.messageSource = messageSource;
		this.userNameService = userNameService;
	}
	
	@ResponseBody
	@GetMapping("/api/subject/translation")
	public String getTransl(@RequestParam("lang") String langCode, @RequestParam("subject") int subjectId) {
		SubjectTranslation translation = subjectTranslationService.findByLangCodeAndSubjectId(langCode, subjectId);
		if (translation == null) {
			return "translation doesn't exist";
		}
		return translation.getTitle();
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
	private Schedule create(@RequestBody EditScheduleDTO editScheduleReq) {
		User user = null;
		Group group = groupService.findById(editScheduleReq.getGroupId());
		Subject subject = subjectService.findById(editScheduleReq.getSubjectId());
		
		if (group == null) {
			throw new IllegalArgumentException("Group was not found!");
		}
		
		if (subject == null) {
			throw new IllegalArgumentException("Subject was not found!");
		}
		
		int userId = editScheduleReq.getUserId();
		if (userId != 0) {
			user = userService.findById(userId);
		}
		
		Schedule schedule = new Schedule();
		schedule.setGroup(group);
		schedule.setWeek(editScheduleReq.getWeek());
		schedule.setDayNum(editScheduleReq.getDayNum());
		schedule.setSubject(subject);
		schedule.setLessonNum(editScheduleReq.getLessonNum());
		schedule.setLessonType(LessonUtil.convertLessonType(editScheduleReq.getLessonType()));
		schedule.setUser(user);
		schedule.setClassroom(editScheduleReq.getClassroom());
		
		return scheduleService.save(schedule);
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
	private EditScheduleResponseDTO update(@RequestBody EditScheduleDTO editScheduleReq, HttpServletRequest request) {
		Locale locale = localeConverterService.getClientLocale(request);
		
		User user = null;
		int userId = editScheduleReq.getUserId();
		Subject subject = subjectService.findById(editScheduleReq.getSubjectId());
		Schedule schedule = scheduleService.findById(editScheduleReq.getScheduleId());
		if (subject == null) {
			throw new IllegalArgumentException("Subject was not found!");
		}
		if (schedule == null) {
			throw new IllegalArgumentException("Schedule was not found!");
		}
		if (userId != 0) {
			user = userService.findById(userId);
		}
		
		
		schedule.setSubject(subject);
		schedule.setLessonType(LessonUtil.convertLessonType(editScheduleReq.getLessonType()));
		schedule.setUser(user);
		schedule.setClassroom(editScheduleReq.getClassroom());
		schedule = scheduleService.update(schedule);
		
		EditScheduleResponseDTO response = new EditScheduleResponseDTO();
		response.setId(schedule.getId());
		response.setSubjectId(schedule.getSubject().getId());
		response.setSubjectTitle(subjectTranslationService.findByLocaleAndSubject(locale, subject).getTitle());
		response.setDayNum(editScheduleReq.getDayNum());
		response.setWeek(editScheduleReq.getWeek());
		response.setLessonNum(editScheduleReq.getLessonNum());
		
		if (user != null) {
			response.setTeacherName(userNameService.makeupUsername(user, locale));
		}
		response.setLessonType(editScheduleReq.getLessonType());
		response.setClassroom(editScheduleReq.getClassroom());
		
		java.util.Locale javaLocale = new java.util.Locale(locale.getCode());
		response.setLessonTypeName(messageSource.getMessage(editScheduleReq.getLessonType(), null, javaLocale));
		return response;
	}
}
