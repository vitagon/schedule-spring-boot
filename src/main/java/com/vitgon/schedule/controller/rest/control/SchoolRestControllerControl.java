package com.vitgon.schedule.controller.rest.control;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.dto.AddSchoolDto;
import com.vitgon.schedule.dto.EditSchoolDto;
import com.vitgon.schedule.dto.SchoolDto;
import com.vitgon.schedule.dto.SchoolDtoControl;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.SchoolConverterService;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SchoolService;
import com.vitgon.schedule.util.StringUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control")
public class SchoolRestControllerControl {
	
	private SchoolService schoolService;
	private SchoolConverterService schoolConverterService;
	private LocaleService localeService;
	private MessageService messageService;
	
	private SchoolDto convertToDto(School school) {
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setId(school.getId());
		schoolDto.setName(school.getName());
		return schoolDto;
	}
	
	@PostMapping("/school")
	@ResponseStatus(HttpStatus.CREATED)
	public SchoolDto addSchool(@RequestBody @Valid AddSchoolDto addSchoolDTO) {
		School school = new School(
				addSchoolDTO.getSchoolName().toLowerCase(),
				StringUtil.applyUnderlyingStyle(addSchoolDTO.getSchoolName()));
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

	@GetMapping("/schools")
	public List<SchoolDtoControl> getSchoolsView(@RequestParam("localeId") int localeId) {
		if (localeId < 0) {
			throw new IllegalArgumentException("Locale id must be equal OR greater than 0!");
		}
		
		ModelAndView model = new ModelAndView("control/schools-list :: schools-list");
		List<SchoolDtoControl> schoolDtoControlList = null;
		
		if (localeId == 0) {
			schoolDtoControlList = schoolConverterService.convertToSchoolDtoControlList();
		} else {
			Optional<Locale> locale = localeService.findById(localeId);
			if (!locale.isPresent()) {
				throw new IllegalArgumentException(String.format("Locale with id=%d was not found!", localeId));
			}
			schoolDtoControlList = schoolConverterService.convertToSchoolDtoControlList(locale.get());
		}
		
		return schoolDtoControlList;
	}
}
