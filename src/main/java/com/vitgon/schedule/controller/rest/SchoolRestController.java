package com.vitgon.schedule.controller.rest;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.SchoolExists;
import com.vitgon.schedule.controller.rest.advice.FieldValidationException;
import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.group.OnCreate;
import com.vitgon.schedule.group.OnUpdate;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.SchoolDtoService;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.util.StringUtil;

@RestController
@RequestMapping("/api/schools")
public class SchoolRestController {
	
	private SchoolService schoolService;
	private SchoolDtoService schoolDtoService;
	
	public SchoolRestController(SchoolService schoolService, SchoolDtoService schoolDtoService) {
		this.schoolService = schoolService;
		this.schoolDtoService = schoolDtoService;
	}

	@GetMapping
	public List<SchoolDto> getSchools() {
		return schoolDtoService.getSchoolDtoListByDefaultLocale();
	}
	
	private SchoolDto convertToDto(School school) {
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setId(school.getId());
		schoolDto.setName(school.getName());
		return schoolDto;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SchoolDto addSchool(@RequestBody @Validated(OnCreate.class) SchoolDto schoolDto) {
		School school = new School(
				schoolDto.getName().toLowerCase(),
				StringUtil.applyUnderlyingStyle(schoolDto.getName()));
		school = schoolService.save(school);
		return convertToDto(school);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess updateSchool(@PathVariable("id") @SchoolExists Integer schoolId,
								   @RequestBody @Validated(OnUpdate.class) SchoolDto schoolDto) {
		Optional<School> schoolOpt = schoolService.findById(schoolId);
		School school = schoolOpt.get();
		school.setName(schoolDto.getName().toLowerCase());
		school.setUrl(StringUtil.applyUnderlyingStyle(schoolDto.getName()));
		schoolService.update(school);
		return new ApiSuccess(new Date(), "You successfully updated school!");
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteSchool(@PathVariable("id") @SchoolExists Integer schoolId) {
		schoolService.deleteById(schoolId);
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully removed school!"));
	}

	@GetMapping("/locale-id/{localeId}")
	public List<SchoolDto> getSchoolsView(@PathVariable @LocaleExists Integer localeId) throws FieldValidationException {
		return schoolDtoService.getSchoolDtoListByLocaleId(localeId);
	}
}
