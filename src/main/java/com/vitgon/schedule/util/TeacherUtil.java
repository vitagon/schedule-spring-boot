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
				String lastname = x.getLastname();
				lastname = lastname.substring(0,1).toUpperCase() + lastname.substring(1);
				
				String firstname = x.getFirstname();
				firstname = firstname.substring(0,1).toUpperCase() + firstname.substring(1);
				
				String middlename = x.getMiddlename();
				middlename = middlename.substring(0,1).toUpperCase() + middlename.substring(1);
				
				return new StringBuilder()
						.append(lastname)
						.append(" ")
						.append(firstname)
						.append(" ")
						.append(middlename)
						.toString();
			})
			.findFirst()
			.orElse("Teacher doesn't have name in this lang");
	}
}
