package com.vitgon.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainViewController {
	
	@RequestMapping("/")
	public String showMainPage() {
		return "main-page";
	}
}
