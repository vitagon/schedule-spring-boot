package com.vitgon.schedule.controller.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlPanelController {
	
	
	@GetMapping("/control")
	public ModelAndView showControlPanel() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("myVar", "Its my custom var!");
		modelAndView.setViewName("control/control-panel");
		return modelAndView;
	}
}
