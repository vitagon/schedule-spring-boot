package com.vitgon.schedule.controller.rest;

import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.projection.MajorProjection;
import com.vitgon.schedule.service.database.MajorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MajorRestController {
	
	private MajorService majorService;

	public MajorRestController(MajorService majorService) {
		this.majorService = majorService;
	}

	@GetMapping("/api/majors/school-id/{schoolId}")
	public List<MajorProjection> getMajors(@PathVariable("schoolId") int schoolId) {
		return majorService.findBySchoolIdAndBrowserDefaultLanguage(schoolId);
	}
	
	@GetMapping("/api/major/{majorId}/max-course-number")
	public int getCourseNumbers(@PathVariable("majorId") int majorId) {
		Optional<Major> major = majorService.findById(majorId);
		if (!major.isPresent()) {
			return 0;
		}
		return major.get().getDuration();
	}
}
