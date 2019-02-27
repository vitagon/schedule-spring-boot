package com.vitgon.schedule.controller.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.LocaleConverterService;
import com.vitgon.schedule.service.MajorTitleService;
import com.vitgon.schedule.service.database.MajorService;
import com.vitgon.schedule.service.database.SchoolService;

@RestController
public class MajorRestController {
	
	private SchoolService schoolService;
	private LocaleConverterService localeConverterService;
	private MajorService majorService;
	private MajorTitleService majorTitleService;

	@GetMapping("/api/school/{schoolId}/majors")
	public Map<Integer, String> getMajors(HttpServletRequest request, @PathVariable("schoolId") int schoolId) {
		Locale locale = localeConverterService.getClientLocale(request);
		School school = schoolService.findById(schoolId);
		Map<Integer, String> majors = new HashMap<>();
		for (Major major : school.getMajors()) {
			majors.put(major.getId(), majorTitleService.getMajorTitle(locale, major));
		}
		return majors;
	}
	
	@GetMapping("/api/major/{majorId}/course-number")
	public int getCourseNumbers(@PathVariable("majorId") int majorId) {
		Major major = majorService.findById(majorId);
		if (major == null) {
			return 0;
		}
		return major.getDuration();
	}
}
