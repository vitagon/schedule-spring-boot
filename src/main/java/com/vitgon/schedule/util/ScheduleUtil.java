package com.vitgon.schedule.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.vitgon.schedule.collection.ScheduleTree;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.pojo.SchedulePOJO;
import com.vitgon.schedule.pojo.TeacherPOJO;

public class ScheduleUtil {
	
	public static final String WEEK_UP = "up";
	public static final String WEEK_DOWN = "down";
	
	private final static Map<Integer, String> DAYS_MAP = new HashMap<>();
	
	static {
		Days[] days = Days.values();
		for (Days day : days) {
			int dayNum = day.getDayNum();
			String title = day.name();
			DAYS_MAP.put(dayNum, title.toLowerCase());
		}
	}
	
	public static void main(String... args) {
		System.out.println(DAYS_MAP);
	}
	
	private enum Days {
		SUNDAY(7), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6);
		
		private int dayNum;
		
		Days(int dayNum) {
			this.dayNum = dayNum;
		}
		
		public int getDayNum() {
			return this.dayNum;
		}
	}
	
	public static ScheduleTree getScheduleTree(List<Schedule> schedules, Locale locale) {
		ScheduleTree scheduleTree = new ScheduleTree();
		for (Schedule schedule : schedules) {
			String dayTitle = DAYS_MAP.get(schedule.getDayNum());
			int lessonNum = schedule.getLessonNum();
			String weekType = schedule.getWeek();
			
			SchedulePOJO schedulePOJO = new SchedulePOJO();
			schedulePOJO.setScheduleId(schedule.getId());
			schedulePOJO.setSubjId(schedule.getSubject().getId());
			schedulePOJO.setSubjectTitle(SubjectUtil.getSubjectTitle(schedule.getSubject(), locale));
			schedulePOJO.setLessonType(LessonUtil.getLessonType(schedule.getLessonType()));
			
			if (schedule.getUser() != null) {
				schedulePOJO.setTeacher(new TeacherPOJO(
						schedule.getUser().getId(),
						UserUtil.makeupUsername(schedule.getUser(), locale)
				));
			}
			
			schedulePOJO.setClassroom(schedule.getClassroom());
			scheduleTree.add(dayTitle, lessonNum, weekType, schedulePOJO);
		}
		return scheduleTree;
	}
	
	/**
	 * 
	 * @param dayTitle
	 * @return
	 */
	public static int getOrderNum(String dayTitle) {
		Map<Integer, String> daysMap = DAYS_MAP;
		return daysMap.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), dayTitle))
				.map(Map.Entry::getKey)
				.findFirst().get();
	}
}
