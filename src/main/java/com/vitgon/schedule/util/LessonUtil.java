package com.vitgon.schedule.util;

public class LessonUtil {

	public static final int LECTURE = 1;
	public static final int PRACTICE = 2;
	
	public static final String LECTURE_STR = "lecture";
	public static final String PRACTICE_STR = "practice";
	
	/**
	 * Turn lesson type from int to string
	 * 
	 * @param lessonType
	 * @return
	 */
	public static String convertLessonType(int lessonType) {
		String lessonTypeStr = null;
		
		switch (lessonType) {
			case LECTURE: {
				lessonTypeStr = LECTURE_STR;
				break;
			}
			case PRACTICE: {
				lessonTypeStr = PRACTICE_STR;
				break;
			}
		}
		return lessonTypeStr;
	}
	
	/**
	 * Turn lesson type from String to int
	 * 
	 * @param lessonType
	 * @return
	 */
	public static int convertLessonType(String lessonType) {
		int lessonTypeInt = 0;
		
		switch (lessonType) {
			case LECTURE_STR: {
				lessonTypeInt = LECTURE;
				break;
			}
			case PRACTICE_STR: {
				lessonTypeInt = PRACTICE;
				break;
			}
		}
		return lessonTypeInt;
	}
	
	public static boolean isValid(String lessonType) {
		if (lessonType.equals(LECTURE_STR) || lessonType.equals(PRACTICE_STR)) {
			return true;
		}
		return false;
	}
}
