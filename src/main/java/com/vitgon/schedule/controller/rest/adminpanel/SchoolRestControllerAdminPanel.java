package com.vitgon.schedule.controller.rest.adminpanel;

import com.fasterxml.jackson.annotation.JsonView;
import com.vitgon.schedule.controller.rest.advice.FieldValidationException;
import com.vitgon.schedule.dto.AddSchoolDto;
import com.vitgon.schedule.dto.EditSchoolDto;
import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.SchoolDtoService;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.util.StringUtil;
import com.vitgon.schedule.validator.LocaleExistsValidator;
import com.vitgon.schedule.view.Views;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/control")
public class SchoolRestControllerAdminPanel {
	
	private SchoolService schoolService;
	private SchoolDtoService schoolDtoService;
	private MessageService messageService;
	private LocaleExistsValidator localeExistsValidator;

	public SchoolRestControllerAdminPanel(SchoolService schoolService, SchoolDtoService schoolDtoService, MessageService messageService, LocaleExistsValidator localeExistsValidator) {
		this.schoolService = schoolService;
		this.schoolDtoService = schoolDtoService;
		this.messageService = messageService;
		this.localeExistsValidator = localeExistsValidator;
	}

	private SchoolDto convertToDto(School school) {
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setId(school.getId());
		schoolDto.setName(school.getName());
		return schoolDto;
	}
	
	@PostMapping("/school")
	@ResponseStatus(HttpStatus.CREATED)
	public SchoolDto addSchool(@RequestBody @Valid AddSchoolDto addSchoolDto) {
		School school = new School(
				addSchoolDto.getName().toLowerCase(),
				StringUtil.applyUnderlyingStyle(addSchoolDto.getName()));
		school = schoolService.save(school);
		return convertToDto(school);
	}
	
	@PutMapping("/school")
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess updateSchool(@RequestBody @Valid EditSchoolDto editSchoolDto) {
		Optional<School> schoolOpt = schoolService.findById(editSchoolDto.getSchoolId());
		if (!schoolOpt.isPresent()) {
			throw new IllegalArgumentException(String.format("School with id=%d name doesn't exist!", editSchoolDto.getSchoolId()));
		}
		School school = schoolOpt.get();
		school.setName(editSchoolDto.getNewSchoolName().toLowerCase());
		school.setUrl(StringUtil.applyUnderlyingStyle(editSchoolDto.getNewSchoolName()));
		schoolService.update(school);
		return new ApiSuccess(new Date(), "You successfully updated school!");
	}
	
	@DeleteMapping("/school")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteSchool(@RequestParam("id") School school) {
		if (school == null) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("schoolId", Arrays.asList(messageService.getMessage("chooseValue")));
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ApiError(new Date(), "School Not Found", errors));
		}
		schoolService.delete(school);
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully removed school!"));
	}

	@JsonView(Views.AdminPanel.class)
	@GetMapping("/schools")
	public List<SchoolDto> getSchoolsView(@RequestParam(required = false) Integer localeId) throws FieldValidationException {
		if (localeId != null) {
			boolean localeIsValid = localeExistsValidator.isValid(localeId, null);
			if (!localeIsValid) {
				throw new FieldValidationException("localeId", "locale.notFound");
			}
			return schoolDtoService.getSchoolDtoListByLocaleIdForAdminPanel(localeId);
		}
		return schoolDtoService.getSchoolDtoListByLocaleIdForAdminPanel();
	}
}
