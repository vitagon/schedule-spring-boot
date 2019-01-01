package com.vitgon.schedule.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.vitgon.schedule.controller.BaseController;

@Controller
public class EditScheduleController extends BaseController {

	@GetMapping("/{groupId}/schedule/edit")
	public String editSchedulePage() {
		return "editSchedule";
	}
}
