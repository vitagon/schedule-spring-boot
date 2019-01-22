package com.vitgon.schedule.controller.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.vitgon.schedule.dto.AddTeacherTranslationDTO;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.model.translation.UserTranslation;
import com.vitgon.schedule.model.translation.pk.UserTranslationId;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.UserService;
import com.vitgon.schedule.service.translation.UserTranslationService;
import com.vitgon.schedule.validator.UserTranslationValidator;

@Controller
public class AddTeacherTranslationController {
	
	@Autowired
	private UserTranslationService userTranslationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LocaleService localeService;
	
	@Autowired
	private UserTranslationValidator userTranslationValidator;
	
	@PostMapping("/control/teacher/translation/add")
	public RedirectView addTeacherTranslation(@Valid AddTeacherTranslationDTO addTeacherTranslationDTO,
											  BindingResult result,
											  RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("activeTab", "addTeacherTranslation");
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTeacherTranslationDTO", result);
			redirectAttributes.addFlashAttribute("addTeacherTranslationDTO", addTeacherTranslationDTO);
			return new RedirectView("/control");
		}
		
		userTranslationValidator.validate(addTeacherTranslationDTO, result);
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTeacherTranslationDTO", result);
			redirectAttributes.addFlashAttribute("addTeacherTranslationDTO", addTeacherTranslationDTO);
			return new RedirectView("/control");
		}
		
		User user = userService.findById(addTeacherTranslationDTO.getUserId());
		Locale locale = localeService.findById(addTeacherTranslationDTO.getLocaleId());
		
		userTranslationService.save(new UserTranslation(
				new UserTranslationId(user, locale),
				addTeacherTranslationDTO.getLastname(),
				addTeacherTranslationDTO.getFirstname(),
				addTeacherTranslationDTO.getMiddlename()
		));
		redirectAttributes.addFlashAttribute("teacherTranslationAddedSuccess", true);
		return new RedirectView("/control");
	}
}
