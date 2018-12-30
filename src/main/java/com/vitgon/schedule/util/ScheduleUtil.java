package com.vitgon.schedule.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Schedule;
import com.vitgon.schedule.util.model.SubjectsPair;

public class ScheduleUtil {
	
	private static final int UP_WEEK = 1;
	private static final int DOWN_WEEK = 2;
	
	private final static Map<Integer, String> DAYS_MAP = getDaysMap();
	
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
	
	public static Map<Integer, String> getDaysMap() {
		Days[] days = Days.values();
		Map<Integer, String> daysMap = new HashMap<>();
		for (Days day : days) {
			int dayNum = day.getDayNum();
			String title = day.name();
			daysMap.put(dayNum, title.toLowerCase());
		}
		return daysMap;
	}
	
	public static Map<String, Map<Integer, SubjectsPair>> getScheduleMap(List<Schedule> schedules, Locale locale) {
		Map<String, Map<Integer, SubjectsPair>> rootMap = new HashMap<>();
		Map<Integer, String> daysMap = DAYS_MAP;
	
		for (Schedule schedule : schedules) {
			String dayTitle = daysMap.get(schedule.getDayNum());
			int subjectNum = schedule.getSubjectNum();
			String subjectTitle = SubjectUtil.getSubjectTitle(schedule.getSubject(), locale);
			int week = schedule.getWeek();
			
			if (rootMap.containsKey(dayTitle)) {
				Map<Integer, SubjectsPair> subjectsPairMap = rootMap.get(dayTitle);
				
				if (subjectsPairMap.containsKey(subjectNum)) {
					SubjectsPair subjectsPair = subjectsPairMap.get(subjectNum);
					setSubjectsPair(subjectsPair, week, subjectTitle);
				} else {
					SubjectsPair subjectsPair = new SubjectsPair();
					subjectsPairMap.put(subjectNum, subjectsPair);
					setSubjectsPair(subjectsPair, week, subjectTitle);
				}
			} else {
				Map<Integer, SubjectsPair> subjectsPairMap = new HashMap<>();
				
				SubjectsPair subjectsPair = new SubjectsPair();
				subjectsPairMap.put(subjectNum, subjectsPair);
				setSubjectsPair(subjectsPair, week, subjectTitle);
				
				rootMap.put(dayTitle, subjectsPairMap);
			}
		}
		
		return sortByDay(rootMap);
	}
	
	public static void setSubjectsPair(SubjectsPair subjectsPair, int week, String subjectTitle) {
		if (week == UP_WEEK) {
			subjectsPair.setUp(subjectTitle);
		}
		
		if (week == DOWN_WEEK){
			subjectsPair.setDown(subjectTitle);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<String, Map<Integer, SubjectsPair>> sortByDay(Map<String, Map<Integer, SubjectsPair>> scheduleMap) {
		List<Entry<String, Map<Integer, SubjectsPair>>> scheduleEntries =
				new ArrayList<Entry<String, Map<Integer, SubjectsPair>>>(scheduleMap.entrySet());
		
		Collections.sort(scheduleEntries, new Comparator<Entry<String, Map<Integer, SubjectsPair>>>() {

			@Override
			public int compare(
					Entry<String, Map<Integer, SubjectsPair>> o1,
					Entry<String, Map<Integer, SubjectsPair>> o2) {
				int dayNum1 = getOrderNum(o1.getKey());
				int dayNum2 = getOrderNum(o2.getKey());
				
				if (dayNum1 > dayNum2) {
					return 1;
				}
				
				if (dayNum1 < dayNum2) {
					return -1;
				}
				
				return 0;
			}
			
		});
		
		Map<String, Map<Integer, SubjectsPair>> sortedMap = new LinkedHashMap<String, Map<Integer, SubjectsPair>>();
        for (Map.Entry<String, Map<Integer, SubjectsPair>> entry : scheduleEntries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
		
		return sortedMap;
	}
	
	public static int getOrderNum(String dayTitle) {
		Map<Integer, String> daysMap = DAYS_MAP;
		return daysMap.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), dayTitle))
				.map(Map.Entry::getKey)
				.findFirst().get();
	}
}
