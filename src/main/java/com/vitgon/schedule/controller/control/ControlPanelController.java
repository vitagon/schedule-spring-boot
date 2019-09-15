package com.vitgon.schedule.controller.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/control")
public class ControlPanelController {

	public ControlPanelController() {
	}

	@GetMapping
	public String showControlPanel() {
		return "admin-panel";
	}
}
