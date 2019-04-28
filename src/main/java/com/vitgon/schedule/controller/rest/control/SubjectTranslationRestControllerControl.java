package com.vitgon.schedule.controller.rest.control;

import java.util.Date;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.dto.AddSubjectTranslationDto;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;
import com.vitgon.schedule.sequence.TranslationValidationSequence;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control/subject/translation")
public class SubjectTranslationRestControllerControl {
	
	private SubjectService subjectService;
	private LocaleService localeService;
	private SubjectTranslationService subjectTranslationService;

	@PostMapping
	public ApiSuccess addSubjectTranslation(@RequestBody @Validated(TranslationValidationSequence.class) AddSubjectTranslationDto addSubjectTranslationDto) {
		Subject subject = subjectService.findById(addSubjectTranslationDto.getSubjectId());
		Locale locale = localeService.findById(addSubjectTranslationDto.getLocaleId());
		
		subjectTranslationService.save(new SubjectTranslation(
				new SubjectTranslationId(subject, locale),
				addSubjectTranslationDto.getTitle()
		));
		
		return new ApiSuccess(new Date(), "You successfully added translation!");
	}
}
