package com.vitgon.schedule.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ScheduleUtil {
	public static final String WEEK_UP = "up";
	public static final String WEEK_DOWN = "down";
	
	public final static Map<Integer, String> DAYS_MAP = createDaysMap();
	
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
	
	/**
	 * 
	 * @return
	 */
	private static Map<Integer, String> createDaysMap() {
		Map<Integer, String> daysMap = new HashMap<>();
		Days[] days = Days.values();
		for (Days day : days) {
			int dayNum = day.getDayNum();
			String title = day.name();
			daysMap.put(dayNum, title.toLowerCase());
		}
		return daysMap;
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
