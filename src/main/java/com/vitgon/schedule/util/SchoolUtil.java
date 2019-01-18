package com.vitgon.schedule.util;

import java.util.ArrayList;
import java.util.List;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Major;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.model.translation.SchoolTranslation;
import com.vitgon.schedule.pojo.school.MajorPojo;
import com.vitgon.schedule.pojo.school.SchoolPojo;

public class SchoolUtil {
	
	public static String getSchoolTitle(School school, Locale locale) {
		if (locale.getCode().equals("en")) {
			return school.getName();
		}
		
		return school.getSchoolTranslations().stream()
			.filter(x -> locale == x.getLocale())
			.map(SchoolTranslation::getTitle)
			.findFirst().get();
	}
	
	public static List<SchoolPojo> prepareSchoolPojos(List<School> schools, Locale locale) {
		
		List<SchoolPojo> schoolsPojos = new ArrayList<>();
		
		for (School school : schools) {
			SchoolPojo schoolPojo = new SchoolPojo();
			schoolPojo.setId(school.getId());
			schoolPojo.setUrl(school.getUrl());
			schoolPojo.setTitle(getSchoolTitle(school, locale));
			
			List<Major> majors = school.getMajors();
			if (majors != null) {
				List<MajorPojo> majorsPojos = new ArrayList<>();
				for (Major major : majors) {
					MajorPojo majorPojo = new MajorPojo();
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
