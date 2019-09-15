package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitgon.schedule.dto.EditScheduleDto;
import com.vitgon.schedule.dto.ScheduleDto;
import com.vitgon.schedule.dto.ScheduleDto.WeekSchedule;
import com.vitgon.schedule.dto.ScheduleDto.WeekSchedule.LessonSchedule;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.projection.ScheduleProjection;
import com.vitgon.schedule.service.database.GroupService;
import com.vitgon.schedule.service.database.ScheduleService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.UserService;

@Service
public class ScheduleDtoService {
	
	private ScheduleService scheduleService;
	private UserDtoService userDtoService;
	private GroupService groupService;
	private SubjectService subjectService;
	private UserService userService;
	private LocaleConverterService localeConverterService;
	
	@Autowired
	public ScheduleDtoService(ScheduleService scheduleService, UserDtoService userDtoService,
							  GroupService groupService, SubjectService subjectService,
							  UserService userService, LocaleConverterService localeConverterService) {
		super();
		this.scheduleService = scheduleService;
		this.userDtoService = userDtoService;
		this.groupService = groupService;
		this.subjectService = subjectService;
		this.userService = userService;
		this.localeConverterService = localeConverterService;
	}
	
	public ScheduleDto getScheduleDtoByGroupId(Integer groupId) {
		List<ScheduleProjection> schedules = scheduleService.findByGroupId(groupId);
		
		ScheduleDto scheduleDto = new ScheduleDto();
		scheduleDto.setGroupId(schedules.get(0).getGroup_id());
		
		for (ScheduleProjection schedule : schedules) {
			String weekType = schedule.getWeek_type();
			
			if (weekType.equals("up")) {
				insertIntoUpWeekSchedule(scheduleDto, schedule);
			} else if (weekType.equals("down")) {
				insertIntoDownWeekSchedule(scheduleDto, schedule);
			}
		}
		
		refuel(scheduleDto);
		sort(scheduleDto);
		return scheduleDto;
	}
	
	private void refuel(ScheduleDto scheduleDto) {
		if (scheduleDto.getUp() == null) {
			scheduleDto.setUp(new WeekSchedule());
		}
		if (scheduleDto.getDown() == null) {
			scheduleDto.setDown(new WeekSchedule());
		}
		refuelLessons(scheduleDto.getUp());
		refuelLessons(scheduleDto.getDown());
	}

	private void refuelLessons(WeekSchedule weekSchedule) {
		List<LessonSchedule> mondayLessonScheduleList = weekSchedule.getMonday();
		mondayLessonScheduleList = refuelLessonsList(mondayLessonScheduleList);
		weekSchedule.setMonday(mondayLessonScheduleList);
		
		List<LessonSchedule> tuesdayLessonScheduleList = weekSchedule.getTuesday();
		tuesdayLessonScheduleList = refuelLessonsList(tuesdayLessonScheduleList);
		weekSchedule.setTuesday(tuesdayLessonScheduleList);
		
		List<LessonSchedule> wednesdayLessonScheduleList = weekSchedule.getWednesday();
		wednesdayLessonScheduleList = refuelLessonsList(wednesdayLessonScheduleList);
		weekSchedule.setWednesday(wednesdayLessonScheduleList);
		
		List<LessonSchedule> thursdayLessonScheduleList = weekSchedule.getThursday();
		thursdayLessonScheduleList = refuelLessonsList(thursdayLessonScheduleList);
		weekSchedule.setThursday(thursdayLessonScheduleList);
		
		List<LessonSchedule> fridayLessonScheduleList = weekSchedule.getFriday();
		fridayLessonScheduleList = refuelLessonsList(fridayLessonScheduleList);
		weekSchedule.setFriday(fridayLessonScheduleList);
		
		List<LessonSchedule> saturdayLessonScheduleList = weekSchedule.getSaturday();
		saturdayLessonScheduleList = refuelLessonsList(saturdayLessonScheduleList);
		weekSchedule.setSaturday(saturdayLessonScheduleList);
		
		List<LessonSchedule> sundayLessonScheduleList = weekSchedule.getSunday();
		sundayLessonScheduleList = refuelLessonsList(sundayLessonScheduleList);
		weekSchedule.setSunday(sundayLessonScheduleList);
	}
	
	private List<LessonSchedule> refuelLessonsList(List<LessonSchedule> lessonScheduleList) {
		if (lessonScheduleList == null) {
			lessonScheduleList = new ArrayList<>(7);
		}
		
		List<LessonSchedule> refuelLessonScheduleList = new ArrayList<>(7);
		for (int lessonNum = 1; lessonNum < 8; lessonNum++) {
			LessonSchedule schedule = findElemByLessonNum(lessonScheduleList, lessonNum);
			if (schedule == null) {
				schedule = new LessonSchedule();
				schedule.setLessonNum(lessonNum);
			}
			refuelLessonScheduleList.add(schedule);
		}
		return refuelLessonScheduleList;
	}
	
	private LessonSchedule findElemByLessonNum(List<LessonSchedule> schedules, int lessonNum) {
		for (LessonSchedule schedule : schedules) {
			if (schedule.getLessonNum() == lessonNum) {
				return schedule;
			}
		}
		return null;
	}

	private ScheduleDto sort(ScheduleDto scheduleDto) {
		sortLessons(scheduleDto.getUp());
		sortLessons(scheduleDto.getDown());
		return scheduleDto;
	}
	
	private void sortLessons(WeekSchedule weekSchedule) {
		List<LessonSchedule> mondayLessonScheduleList = weekSchedule.getMonday();
		Collections.sort(mondayLessonScheduleList);
		
		List<LessonSchedule> tuesdayLessonScheduleList = weekSchedule.getTuesday();
		Collections.sort(tuesdayLessonScheduleList);
		
		List<LessonSchedule> wednesdayLessonScheduleList = weekSchedule.getWednesday();
		Collections.sort(wednesdayLessonScheduleList);
		
		List<LessonSchedule> thursdayLessonScheduleList = weekSchedule.getThursday();
		Collections.sort(thursdayLessonScheduleList);
		
		List<LessonSchedule> fridayLessonScheduleList = weekSchedule.getFriday();
		Collections.sort(fridayLessonScheduleList);
		
		List<LessonSchedule> saturdayLessonScheduleList = weekSchedule.getSaturday();
		Collections.sort(saturdayLessonScheduleList);
		
		List<LessonSchedule> sundayLessonScheduleList = weekSchedule.getSunday();
		Collections.sort(sundayLessonScheduleList);
	}

	private void insertIntoUpWeekSchedule(ScheduleDto scheduleDto, ScheduleProjection schedule) {
		WeekSchedule weekSchedule = prepareWeekSchedule(scheduleDto.getUp(), schedule);
		scheduleDto.setUp(weekSchedule);
	}
	
	private void insertIntoDownWeekSchedule(ScheduleDto scheduleDto, ScheduleProjection schedule) {
		WeekSchedule weekSchedule = prepareWeekSchedule(scheduleDto.getDown(), schedule);
		scheduleDto.setDown(weekSchedule);
	}
	
	private List<LessonSchedule> addToLessonList(List<LessonSchedule> lessonScheduleList, LessonSchedule lessonSchedule) {
		if (lessonScheduleList == null) {
			lessonScheduleList = new ArrayList<>(7);
		}
		lessonScheduleList.add(lessonSchedule);
		return lessonScheduleList;
	}
	
	private ScheduleDto.WeekSchedule prepareWeekSchedule(WeekSchedule weekSchedule, ScheduleProjection schedule) {
		if (weekSchedule == null) {
			weekSchedule = new WeekSchedule();
		}
		int dayNum = schedule.getDay_num();
		LessonSchedule lessonSchedule = prepareLessonSchedule(schedule);
		List<LessonSchedule> lessonScheduleList;
		
		switch (dayNum) {
			case 1:
				lessonScheduleList = weekSchedule.getMonday();
				lessonScheduleList = addToLessonList(lessonScheduleList, lessonSchedule);
				weekSchedule.setMonday(lessonScheduleList);
				break;
			case 2:
				lessonScheduleList = weekSchedule.getTuesday();
				lessonScheduleList = addToLessonList(lessonScheduleList, lessonSchedule);
				weekSchedule.setTuesday(lessonScheduleList);
				break;
			case 3:
				lessonScheduleList = weekSchedule.getWednesday();
				lessonScheduleList = addToLessonList(lessonScheduleList, lessonSchedule);
				weekSchedule.setWednesday(lessonScheduleList);
				break;
			case 4:
				lessonScheduleList = weekSchedule.getThursday();
				lessonScheduleList = addToLessonList(lessonScheduleList, lessonSchedule);
				weekSchedule.setThursday(lessonScheduleList);
				break;
			case 5:
				lessonScheduleList = weekSchedule.getFriday();
				lessonScheduleList = addToLessonList(lessonScheduleList, lessonSchedule);
				weekSchedule.setFriday(lessonScheduleList);
				break;
			case 6:
				lessonScheduleList = weekSchedule.getSaturday();
				lessonScheduleList = addToLessonList(lessonScheduleList, lessonSchedule);
				weekSchedule.setSaturday(lessonScheduleList);
				break;
			case 7:
				lessonScheduleList = weekSchedule.getSunday();
				lessonScheduleList = addToLessonList(lessonScheduleList, lessonSchedule);
				weekSchedule.setSunday(lessonScheduleList);
				break;
		}
		return weekSchedule;
	}
	
	private LessonSchedule prepareLessonSchedule(ScheduleProjection schedule) {
		LessonSchedule lessonSchedule = new WeekSchedule.LessonSchedule();
		lessonSchedule.setId(schedule.getId());
		lessonSchedule.setLessonNum(schedule.getLesson_num());
		lessonSchedule.setTime(schedule.getLesson_time());
		lessonSchedule.setSubjectId(schedule.getSubject_id());
		lessonSchedule.setSubjectName(schedule.getSubject_name());
		lessonSchedule.setLessonTypeId(schedule.getLesson_type_id());
		lessonSchedule.setLessonTypeName(schedule.getLesson_type_name());
		lessonSchedule.setTeacherId(schedule.getUser_id());
		String teacherFullName = userDtoService.makeupFullname(
				schedule.getLastname(), schedule.getFirstname(), schedule.getMiddlename());
		lessonSchedule.setTeacherName(teacherFullName);
		lessonSchedule.setClassroom(schedule.getClassroom());
		return lessonSchedule;
	}
	
	
	
	public Schedule editScheduleDtoToSchedule(EditScheduleDto editScheduleDto) {
		Schedule schedule = new Schedule();
		
		Optional<Group> group = groupService.findById(editScheduleDto.getGroupId());
		schedule.setGroup(group.get());
		
		schedule.setWeek(editScheduleDto.getWeek());
		schedule.setDayNum(editScheduleDto.getDay().getDayNum());
		schedule.setLessonNum(editScheduleDto.getLessonNum());
		schedule.setLessonType(editScheduleDto.getLessonTypeId());
		
		Optional<Subject> subject = subjectService.findById(editScheduleDto.getSubjectId());
		schedule.setSubject(subject.get());
		
		Optional<User> user = userService.findById(editScheduleDto.getUserId());
		if (user.isPresent()) {
			schedule.setUser(user.get());
		}
		
		schedule.setClassroom(editScheduleDto.getClassroom());
		return schedule;
	}

	public LessonSchedule getLessonScheduleByScheduleId(Integer id) {
		Locale locale = localeConverterService.getClientLocale();
		ScheduleProjection scheduleProjection = scheduleService.getById(id, locale.getId());
		LessonSchedule lessonSchedule = null;
		if (scheduleProjection != null) {
			lessonSchedule = prepareLessonSchedule(scheduleProjection);
		}
		return lessonSchedule;
	}
}
