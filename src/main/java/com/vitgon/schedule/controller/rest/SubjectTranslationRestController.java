package com.vitgon.schedule.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.SubjectExists;
import com.vitgon.schedule.controller.rest.advice.FieldValidationException;
import com.vitgon.schedule.controller.rest.advice.Violation;
import com.vitgon.schedule.dto.SubjectTranslationDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api/translations")
public class SubjectTranslationRestController {

	private SubjectTranslationService subjectTranslationService;

	@GetMapping("/subjects/{subjectId}/locales/{localeId}")
	public ResponseEntity<?> getSubjectTranslation(@PathVariable("subjectId") @SubjectExists Integer subjectId,
												   @PathVariable("localeId") @LocaleExists Integer localeId) throws FieldValidationException {
		
		Optional<SubjectTranslation> subjectTranslation = subjectTranslationService.findByLocaleIdAndSubjectId(localeId,
				subjectId);
		if (!subjectTranslation.isPresent()) {
			throw new FieldValidationException("localeId", "translation.notFound");
		}
		SubjectTranslationDto subjectTranslationDto = new SubjectTranslationDto(
				subjectId,
				localeId,
				subjectTranslation.get().getTranslation());
		return ResponseEntity.ok(subjectTranslationDto);
	}

	@PostMapping("/subjects/{subjectId}/locales/{localeId}")
	public ResponseEntity<?> addSubjectTranslation(@RequestBody @Validated SubjectTranslationDto subjectTranslationDto) throws FieldValidationException {
		Optional<SubjectTranslation> subjectTranslation = subjectTranslationService
				.findByLocaleIdAndSubjectId(subjectTranslationDto.getLocaleId(), subjectTranslationDto.getSubjectId());
		if (!subjectTranslation.isPresent()) {
			subjectTranslationService.save(subjectTranslationDto.getSubjectId(), subjectTranslationDto.getLocaleId(),
					subjectTranslationDto.getTranslation());
		} else {
			throw new FieldValidationException("translation", "Duplicate.translation");
		}

		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully added translation!"));
	}

	@PutMapping("/subjects/{subjectId}/locales/{localeId}")
	public ResponseEntity<?> editSubjectTranslation(@RequestBody @Validated SubjectTranslationDto subjectTranslationDto) throws FieldValidationException {

		Optional<SubjectTranslation> subjectTranslationOpt = subjectTranslationService
				.findByLocaleIdAndSubjectId(
						subjectTranslationDto.getLocaleId(),
						subjectTranslationDto.getSubjectId()
				);

		if (subjectTranslationOpt.isPresent()) {
			SubjectTranslation subjectTranslation = subjectTranslationOpt.get();
			subjectTranslation.setTranslation(subjectTranslationDto.getTranslation());
			subjectTranslationService.update(subjectTranslation);
			return ResponseEntity.ok().body(null);
		}

		throw new FieldValidationException("localeId", "translation.notFound");
	}

	@DeleteMapping("/subjects/{subjectId}/locales/{localeId}")
	public ResponseEntity<?> removeSubjectTranslation(@PathVariable("subjectId") @SubjectExists Integer subjectId,
													  @PathVariable("localeId") @LocaleExists Integer localeId) throws FieldValidationException {
		Optional<SubjectTranslation> subjectTranslationOpt = subjectTranslationService
				.findByLocaleIdAndSubjectId(localeId, subjectId);

		if (subjectTranslationOpt.isPresent()) {
			subjectTranslationService.deleteBySubjectIdAndLocaleId(subjectId, localeId);
			return ResponseEntity.ok().body(null);
		}
		
		throw new FieldValidationException("localeId", "translation.notFound");
	}
}
