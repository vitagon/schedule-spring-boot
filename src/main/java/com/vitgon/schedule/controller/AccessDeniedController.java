package com.vitgon.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessDeniedController {

	@GetMapping("/access-denied")
	public ModelAndView showAccessDeniedPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("auth/access-denied");
		return modelAndView;
	}
}
