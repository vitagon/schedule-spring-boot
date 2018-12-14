package com.vitgon.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	
	@RequestMapping("/")
	public String redirectToMain() {
		return "redirect:/en/";
	}
}
