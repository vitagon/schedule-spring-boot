package com.vitgon.schedule.controller.rest;

import java.util.Date;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dto.AddTeacherTranslationDto;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.translation.UserTranslation;
import com.vitgon.schedule.model.database.translation.pk.UserTranslationId;
import com.vitgon.schedule.sequence.TranslationValidationSequence;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.service.database.translation.UserTranslationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control/teacher/translation")
public class TeacherTranslationRestController {
	
	private UserTranslationService userTranslationService;
	private UserService userService;
	private LocaleService localeService;

	@ResponseBody
	@PostMapping
	public ApiSuccess addTeacherTranslation(@RequestBody @Validated(TranslationValidationSequence.class) AddTeacherTranslationDto addTeacherTranslationDTO) {
		User user = userService.findById(addTeacherTranslationDTO.getUserId());
		Locale locale = localeService.findById(addTeacherTranslationDTO.getLocaleId());
		
		userTranslationService.save(new UserTranslation(
				new UserTranslationId(user, locale),
				addTeacherTranslationDTO.getLastname(),
				addTeacherTranslationDTO.getFirstname(),
				addTeacherTranslationDTO.getMiddlename()
		));
		return new ApiSuccess(new Date(), "You successfully added translation!");
	}
}
