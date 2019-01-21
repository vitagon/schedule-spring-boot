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

@Controller
public class AddTeacherTranslation {
	
	@Autowired
	private UserTranslationService userTranslationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LocaleService localeService;
	
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
		
		if (!checkIfTeacherTranslationExists(addTeacherTranslationDTO)) {
			result.rejectValue("localeId", "UniqueTeacherTranslation.teacherTranslation");
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
	
	private boolean checkIfTeacherTranslationExists(AddTeacherTranslationDTO addTeacherTranslationDTO) {
		// get user
		if (addTeacherTranslationDTO.getUserId() <= 0) {
			return false;
		}
		User user = userService.findById(addTeacherTranslationDTO.getUserId());
		if (user == null) {
			return false;
		}
		
		// get locale
		if (addTeacherTranslationDTO.getLocaleId() <= 0) {
			return false;
		}
		Locale locale = localeService.findById(addTeacherTranslationDTO.getLocaleId());
		if (locale == null) {
			return false;
		}
		
		// check if user translation for this locale already exists
		boolean userTranslationExists = user.getUserTranslations().stream()
			.filter(userTranslation -> locale == userTranslation.getUserTranslationId().getLocale())
			.findFirst()
			.isPresent();
		
		if (!userTranslationExists) {
			return true;
		}
		
		return false;
	}
}
