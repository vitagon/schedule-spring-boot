package com.vitgon.schedule.util;

public class LessonUtil {

	private static final int LECTURE = 1;
	private static final int PRACTICE = 2;
	
	/**
	 * Turn lesson type from int to string,
	 * then in i18n folder we can define translations for those strings,
	 * i.e. lecture, practice
	 * 
	 * @param lessonType
	 * @return
	 */
	public static String getLessonType(int lessonType) {
		String lessonTypeStr = null;
		
		switch (lessonType) {
			case LECTURE: {
				lessonTypeStr = "lecture";
				break;
			}
			case PRACTICE: {
				lessonTypeStr = "practice";
				break;
			}
		}
		return lessonTypeStr;
	}
}
