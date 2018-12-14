package com.vitgon.schedule.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitgon.schedule.controller.BaseController;

@Controller
public class MainPageController extends BaseController {
	
	@RequestMapping("/")
	public String showMainPage() {
		return "mainPage";
	}
}
