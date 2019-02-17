package com.vitgon.schedule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.util.SchoolUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SchoolMapperService {
	
	private SchoolService schoolService;

	public Map<Integer, String> mapAllSchoolsToMap(Locale locale) {
		List<School> schools = schoolService.findAll();
		Map<Integer, String> schoolsMap = new HashMap<>();
		for (School school : schools) {
			schoolsMap.put(school.getId(), SchoolUtil.getSchoolTitle(school, locale));
		}
		return schoolsMap;
	}
}
