package com.vitgon.schedule.controller.rest;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.SchoolExists;
import com.vitgon.schedule.controller.rest.advice.FieldValidationException;
import com.vitgon.schedule.dto.SchoolTranslationDto;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.service.database.translation.SchoolTranslationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/translations")
@RestController
public class SchoolTranslationRestController {
	
	private SchoolTranslationService schoolTranslationService;
	
	@GetMapping("/schools/{schoolId}/locales/{localeId}")
	public ResponseEntity<?> getSchoolTranslation(@PathVariable("schoolId") @SchoolExists Integer schoolId,
												  @PathVariable("localeId") @LocaleExists Integer localeId) throws FieldValidationException {
		
		Optional<SchoolTranslation> schoolTranslation = schoolTranslationService.findByLocaleIdAndSchoolId(localeId,
				schoolId);
		if (!schoolTranslation.isPresent()) {
			throw new FieldValidationException("localeId", "translation.notFound");
		}
		SchoolTranslationDto schoolTranslationDto = new SchoolTranslationDto(
				schoolId,
				localeId,
				schoolTranslation.get().getTranslation());
		return ResponseEntity.ok(schoolTranslationDto);
	}
}
