package com.vitgon.schedule.controller.rest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.vitgon.schedule.dto.SubjectTranslationDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/translations")
public class SubjectTranslationRestController {
	
	private SubjectTranslationService subjectTranslationService;
	private SubjectService subjectService;
	private LocaleService localeService;
	private MessageService messageService;
	
	@GetMapping("/subjects/{subjectId}/locales/{localeId}")
	public ResponseEntity<?> getSubjectTranslation(@PathVariable("subjectId") Integer subjectId,
												   @PathVariable("localeId") Integer localeId) {
		Optional<Subject> subject = subjectService.findById(subjectId);
		if (!subject.isPresent()) {
			return ResponseEntity.badRequest().body(createApiError("subjectId", "subject.notFound"));
		}
		
		Optional<Locale> locale = localeService.findById(localeId);
		if (!locale.isPresent()) {
			return ResponseEntity.badRequest().body(createApiError("localeId", "locale.notFound"));
		}
		Optional<SubjectTranslation> subjectTranslation = subjectTranslationService.findByLocaleIdAndSubjectId(localeId, subjectId);
		if (!subjectTranslation.isPresent()) {
			return ResponseEntity.badRequest().body(createApiError("localeId", "translation.notFound"));
		}
		SubjectTranslationDto subjectTranslationDto = new SubjectTranslationDto(subjectId, localeId, subjectTranslation.get().getTranslation());
		return ResponseEntity.ok(subjectTranslationDto);
	}

	@PostMapping("/subjects/{subjectId}/locales/{localeId}")
	public ResponseEntity<?> addSubjectTranslation(@PathVariable("subjectId") Integer subjectId,
												   @PathVariable("localeId") Integer localeId,
												   @RequestBody @Validated SubjectTranslationDto subjectTranslationDto) {
		Optional<Subject> subjectOpt = subjectService.findById(subjectId);
		if (!subjectOpt.isPresent()) {
			ApiError apiError = createApiError("subjectId", "validation.chooseSubject");
			return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Locale> locale = localeService.findById(localeId);
		if (!locale.isPresent()) {
			return ResponseEntity.badRequest().body(createApiError("localeId", "locale.notFound"));
		}
		
		Optional<SubjectTranslation> subjectTranslation = subjectTranslationService.findByLocaleIdAndSubjectId(localeId, subjectId);
		if (!subjectTranslation.isPresent()) {
			subjectTranslationService.save(
					subjectId,
					localeId,
					subjectTranslationDto.getTranslation()
			);
		} else {
			ApiError apiError = createApiError("translation", "Duplicate.translation");
			return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully added translation!"));
	}
	
	@PutMapping("/subjects/{subjectId}/locales/{localeId}")
	public ResponseEntity<?> editSubjectTranslation(@PathVariable("subjectId") Integer subjectId,
									   				@PathVariable("localeId") Integer localeId,
									   				@RequestBody @Validated SubjectTranslationDto subjectTranslationDto) {
		Optional<Subject> subjectOpt = subjectService.findById(subjectId);
		if (!subjectOpt.isPresent()) {
			ApiError apiError = createApiError("subjectId", "validation.chooseSubject");
			return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Locale> locale = localeService.findById(localeId);
		if (!locale.isPresent()) {
			return ResponseEntity.badRequest().body(createApiError("localeId", "locale.notFound"));
		}
		
		Optional<SubjectTranslation> subjectTranslationOpt = subjectTranslationService.findByLocaleIdAndSubjectId(localeId, subjectId);
		
		if (subjectTranslationOpt.isPresent()) {
			SubjectTranslation subjectTranslation = subjectTranslationOpt.get();
			subjectTranslation.setTranslation(subjectTranslationDto.getTranslation());
			subjectTranslationService.update(subjectTranslation);
			return ResponseEntity.ok().body(null); 
		}
		
		return ResponseEntity.badRequest().body(createApiError("localeId", "translation.notFound"));
	}
	
	@DeleteMapping("/subjects/{subjectId}/locales/{localeId}")
	public ResponseEntity<?> removeSubjectTranslation(@PathVariable("subjectId") Integer subjectId,
									   				  @PathVariable("localeId") Integer localeId) {
		Optional<Subject> subjectOpt = subjectService.findById(subjectId);
		if (!subjectOpt.isPresent()) {
			ApiError apiError = createApiError("subjectId", "validation.chooseSubject");
			return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
		}
		
		Optional<Locale> locale = localeService.findById(localeId);
		if (!locale.isPresent()) {
			return ResponseEntity.badRequest().body(createApiError("localeId", "locale.notFound"));
		}
		
		Optional<SubjectTranslation> subjectTranslationOpt = subjectTranslationService.findByLocaleIdAndSubjectId(localeId, subjectId);
		
		if (subjectTranslationOpt.isPresent()) {
			subjectTranslationService.deleteBySubjectIdAndLocaleId(subjectId, localeId);
			return ResponseEntity.ok().body(null);
		}
		
		return ResponseEntity.badRequest().body(createApiError("localeId", "translation.notFound"));
	}
	
	private ApiError createApiError(String propertyName, String i18nCode) {
		Map<String, List<String>> errors = new HashMap<>();
		errors.put(propertyName, Arrays.asList(messageService.getMessage(i18nCode)));
		return new ApiError(new Date(), "Validation Failed", errors);
	}
}
