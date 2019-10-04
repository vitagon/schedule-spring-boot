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
import com.vitgon.schedule.annotation.validation.MajorExists;
import com.vitgon.schedule.annotation.validation.SchoolExists;
import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.group.OnCreate;
import com.vitgon.schedule.group.OnUpdate;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.projection.MajorProjection;
import com.vitgon.schedule.service.MajorDtoService;
import com.vitgon.schedule.service.database.MajorService;

@RestController
@RequestMapping("/api/majors")
public class MajorRestController {
	
	private MajorService majorService;
	private MajorDtoService majorDtoService;

	public MajorRestController(MajorService majorService, MajorDtoService majorDtoService) {
		this.majorService = majorService;
		this.majorDtoService = majorDtoService;
	}

	@GetMapping("/school-id/{schoolId}")
	public List<MajorProjection> getMajorsBySchoolId(@PathVariable("schoolId") @SchoolExists Integer schoolId) {
		return majorService.findBySchoolIdAndBrowserDefaultLanguage(schoolId);
	}
	
	@GetMapping("/{id}/max-course-number")
	public int getCourseNumbers(@PathVariable("id") @MajorExists Integer majorId) {
		Optional<Major> major = majorService.findById(majorId);
		return major.get().getDuration();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MajorDto addMajor(@RequestBody @Validated(OnCreate.class) MajorDto majorDto) {
		Major major = majorDtoService.convertToEntity(majorDto);
		major = majorService.save(major);
		return majorDtoService.getById(major.getId());
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess updateMajor(@PathVariable("id") @MajorExists Integer majorId,
								  @RequestBody @Validated(OnUpdate.class) MajorDto majorDto) {
		Optional<Major> majorOpt = majorService.findById(majorId);
		Major major = majorDtoService.convertToEntity(majorDto, majorOpt.get());
		majorService.update(major);
		return new ApiSuccess(new Date(), "You successfully updated major!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMajor(@PathVariable("id") @MajorExists Integer majorId) {
		majorService.deleteById(majorId);
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully removed major!"));
	}
	
	@GetMapping("/locale-id/{localeId}")
	@ResponseStatus(HttpStatus.OK)
	public List<MajorDto> getMajorsByLocaleId(@PathVariable("localeId") @LocaleExists Integer localeId) {
		return majorDtoService.getAllByLocaleId(localeId);
	}

	@GetMapping("{id}")
	public MajorDto getMajor(@PathVariable("id") @MajorExists Integer majorId) {
		return majorDtoService.getById(majorId);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<MajorDto> getMajors() {
		return majorDtoService.getAllByDefaultLocale();
	}
}
