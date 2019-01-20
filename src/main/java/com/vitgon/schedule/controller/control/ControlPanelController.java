package com.vitgon.schedule.controller.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.form.AddSubjectDTO;

@Controller
public class ControlPanelController {
	
	
	@GetMapping("/control")
	public ModelAndView showControlPanel(ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (!modelMap.containsAttribute("addSubjectDTO")) {
			modelAndView.addObject(new AddSubjectDTO());
		}
		
		if (!modelMap.containsAttribute("activeTab")) {
			modelAndView.addObject("activeTab", "main");
		}
		
		modelAndView.setViewName("control/control-panel");
		return modelAndView;
	}
}
