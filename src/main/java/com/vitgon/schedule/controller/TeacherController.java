package com.vitgon.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vitgon.schedule.model.translation.TeacherTranslation;
import com.vitgon.schedule.service.translation.TeacherTranslationService;

@Controller
@RequestMapping("/api")
public class TeacherController {
	
	@Autowired
	private TeacherTranslationService teacherTranslService;
	
	@GetMapping("/teacher/search")
	@ResponseBody
	public List<TeacherTranslation> searchTeachers(@RequestParam("keyword") String keyword) {
//		Specification<Teacher> spec = new TeacherSpecification(keyword);
//		return teacherService.findAll(spec);
		return teacherTranslService.searchTeachers(keyword);
	}
}
