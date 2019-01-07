package com.vitgon.schedule.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditScheduleController {

	@GetMapping("/{groupId}/schedule/edit")
	public String editSchedulePage() {
		return "editSchedule";
	}
}
