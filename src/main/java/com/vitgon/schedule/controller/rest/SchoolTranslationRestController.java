package com.vitgon.schedule.controller.rest;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

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
import com.vitgon.schedule.annotation.validation.SchoolExists;
import com.vitgon.schedule.controller.rest.advice.FieldValidationException;
import com.vitgon.schedule.dto.SchoolTranslationDto;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.service.database.translation.SchoolTranslationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/translations")
public class SchoolTranslationRestController {
	
	private SchoolTranslationService schoolTranslationService;

	public SchoolTranslationRestController(SchoolTranslationService schoolTranslationService) {
		this.schoolTranslationService = schoolTranslationService;
	}

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
	
	@PostMapping("/schools/{schoolId}/locales/{localeId}")
	public ResponseEntity<?> addSchoolTranslation(@RequestBody @Valid SchoolTranslationDto schoolTranslationDto) throws FieldValidationException {
		Optional<SchoolTranslation> schoolTranslationOpt = schoolTranslationService
				.findByLocaleIdAndSchoolId(
						schoolTranslationDto.getLocaleId(),
						schoolTranslationDto.getSchoolId()
				);
		
		if (!schoolTranslationOpt.isPresent()) {
			schoolTranslationService.save(schoolTranslationDto.getSchoolId(), schoolTranslationDto.getLocaleId(),
					schoolTranslationDto.getTranslation());
		} else {
			throw new FieldValidationException("translation", "Duplicate.translation");
		}

		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully added translation!"));
	}

	@PutMapping("/schools/{schoolId}/locales/{localeId}")
	public ResponseEntity<?> editSchoolTranslation(@RequestBody @Validated SchoolTranslationDto schoolTranslationDto) throws FieldValidationException {

		Optional<SchoolTranslation> schoolTranslationOpt = schoolTranslationService
				.findByLocaleIdAndSchoolId(
						schoolTranslationDto.getLocaleId(),
						schoolTranslationDto.getSchoolId()
				);

		if (schoolTranslationOpt.isPresent()) {
			SchoolTranslation schoolTranslation = schoolTranslationOpt.get();
			schoolTranslation.setTranslation(schoolTranslationDto.getTranslation());
			schoolTranslationService.update(schoolTranslation);
			return ResponseEntity.ok().body(null);
		}

		throw new FieldValidationException("localeId", "translation.notFound");
	}

	@DeleteMapping("/schools/{schoolId}/locales/{localeId}")
	public ResponseEntity<?> removeSubjectTranslation(@PathVariable("schoolId") @SchoolExists Integer schoolId,
													  @PathVariable("localeId") @LocaleExists Integer localeId) throws FieldValidationException {
		Optional<SchoolTranslation> schoolTranslationOpt = schoolTranslationService
				.findByLocaleIdAndSchoolId(localeId, schoolId);

		if (schoolTranslationOpt.isPresent()) {
			schoolTranslationService.deleteBySchoolIdAndLocaleId(schoolId, localeId);
			return ResponseEntity.ok().body(null);
		}
		
		throw new FieldValidationException("localeId", "translation.notFound");
	}
}
