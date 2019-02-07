package com.vitgon.schedule.util;

public class LessonUtil {

	private static final int LECTURE = 1;
	private static final int PRACTICE = 2;
	
	private static final String LECTURE_STR = "lecture";
	private static final String PRACTICE_STR = "practice";
	
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
}
