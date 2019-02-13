package com.vitgon.schedule.util;

import java.util.ArrayList;
import java.util.List;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.pojo.MajorPOJO;
import com.vitgon.schedule.pojo.SchoolPOJO;

public class SchoolUtil {
	
	public static String getSchoolTitle(School school, Locale locale) {
		if (locale.getCode().equals("en")) {
			return school.getName();
		}
		
		return school.getTranslations().stream()
			.filter(x -> locale == x.getLocale())
			.map(SchoolTranslation::getTitle)
			.findFirst().get();
	}
	
	public static List<SchoolPOJO> prepareSchoolPojos(List<School> schools, Locale locale) {
		
		List<SchoolPOJO> schoolsPojos = new ArrayList<>();
		
		for (School school : schools) {
			SchoolPOJO schoolPojo = new SchoolPOJO();
			schoolPojo.setId(school.getId());
			schoolPojo.setUrl(school.getUrl());
			schoolPojo.setTitle(getSchoolTitle(school, locale));
			
			List<Major> majors = school.getMajors();
			if (majors != null) {
				List<MajorPOJO> majorsPojos = new ArrayList<>();
				for (Major major : majors) {
					MajorPOJO majorPojo = new MajorPOJO();
					majorPojo.setTitle(MajorUtil.getMajorTitle(major, locale));
					majorPojo.setUrl(major.getUrl());
					
					majorsPojos.add(majorPojo);
				}
				schoolPojo.setMajors(majorsPojos);
			}
			
			schoolsPojos.add(schoolPojo);
		}
		return schoolsPojos;
	}
	
	public static String getAbbreviation(String title) {
		return null;
	}
}
