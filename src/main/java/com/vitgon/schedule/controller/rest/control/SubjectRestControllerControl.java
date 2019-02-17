package com.vitgon.schedule.controller.rest.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.vitgon.schedule.dto.AddSubjectDTO;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;

@Controller
public class SubjectRestControllerControl {
	
	private SubjectService subjectService;

	@Autowired
	public SubjectRestControllerControl(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@PostMapping("/control/subject/add")
	public RedirectView addSubject(
			@Valid AddSubjectDTO addSubjectDTO,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("activeTab", "addSubject");
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSubjectDTO", bindingResult);
			redirectAttributes.addFlashAttribute("addSubjectDTO", addSubjectDTO);
			return new RedirectView("/control");
		}
		
		subjectService.save(new Subject(addSubjectDTO.getSubjectName().toLowerCase()));
		redirectAttributes.addFlashAttribute("subjectAddedSuccess", true);
		
		return new RedirectView("/control");
	}
}
