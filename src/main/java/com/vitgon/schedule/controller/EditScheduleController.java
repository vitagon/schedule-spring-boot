package com.vitgon.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vitgon.schedule.model.request.EditScheduleRequest;

@Controller
public class EditScheduleController {

	@ResponseBody
	@PostMapping("/api/schedule/edit")
	public String editSchedulePage(@ModelAttribute EditScheduleRequest editScheduleReq) {
		int scheduleId = editScheduleReq.getScheduleId();
		String subjectTitle = editScheduleReq.getSubjectTitle();
		String lessonType = editScheduleReq.getLessonType();
		String teacherId = editScheduleReq.getTeacherId();
		String classroom = editScheduleReq.getClassroom();
		
		if (scheduleId != 0) {
			System.out.println("We should create schedule!");
		}
		
		System.out.println(editScheduleReq);
		return "we got it";
	}
}
