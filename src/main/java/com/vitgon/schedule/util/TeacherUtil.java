package com.vitgon.schedule.util;

import java.util.List;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Teacher;
import com.vitgon.schedule.model.translation.TeacherTranslation;

public class TeacherUtil {
	
	/**
	 * Make up teacher name | Combine firstname, lastname and middlename
	 * Will return ex.:"Jordan Daniel"
	 * 
	 * @return 
	 */
	public static String makeUpTeacherName(Teacher teacher, Locale locale) {
		if (teacher == null) {
			return null;
		}
		
		List<TeacherTranslation> teacherTransl = teacher.getTeacherTranslations();
		
		return teacherTransl.stream()
			.filter(x -> x.getLocale() == locale)
			.map(x -> {
				return new StringBuilder()
						.append(x.getLastname())
						.append(" ")
						.append(x.getFirstname())
						.append(" ")
						.append(x.getMiddlename())
						.toString();
			})
			.findFirst()
			.orElse("Teacher doesn't have name in this lang");
	}
}
