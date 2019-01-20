package com.vitgon.schedule.controller.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.vitgon.schedule.dto.form.AddSubjectDTO;
import com.vitgon.schedule.service.SubjectService;

@Controller
public class AddSubjectController {
	
	@Autowired
	private SubjectService subjectService;

	@PostMapping("/control/subject/add")
	public RedirectView addSubject(
			@Valid AddSubjectDTO addSubjectDTO,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSubjectDTO", bindingResult);
			redirectAttributes.addFlashAttribute("addSubjectDTO", addSubjectDTO);
			redirectAttributes.addFlashAttribute("activeTab", "addSubject");
			return new RedirectView("/control");
		}
		
		return new RedirectView("/control");
		
//		if (name != null && name.matches("[A-Za-z]")) {
//			subjectService.save(new Subject(name));
//			modelAndView.addObject(new AddSubjectDTO());
//		} else {
//			bindingResult.rejectValue("subjectName", "add.subject.length");
//			bindingResult.rejectValue("subjectName", "add.subject.latin");
//		}
	}
}
