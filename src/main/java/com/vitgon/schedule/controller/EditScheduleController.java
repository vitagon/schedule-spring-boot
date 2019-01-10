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
		System.out.println(editScheduleReq);
		return "we got it";
	}
}
