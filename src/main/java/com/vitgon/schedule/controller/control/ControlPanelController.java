package com.vitgon.schedule.controller.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/control")
public class ControlPanelController {

	@GetMapping
	public String showControlPanel(HttpServletRequest request) {
		return "forward:/admin-panel.html";
	}
}
