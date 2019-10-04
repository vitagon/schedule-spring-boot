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
import com.vitgon.schedule.annotation.validation.SubjectExists;
import com.vitgon.schedule.dto.SubjectDto;
import com.vitgon.schedule.group.OnCreate;
import com.vitgon.schedule.group.OnUpdate;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.SubjectDtoService;
import com.vitgon.schedule.service.database.SubjectService;

@RestController
@RequestMapping("/api/subjects")
public class SubjectRestController {
	
	private SubjectDtoService subjectDtoService;
	private SubjectService subjectService;

	public SubjectRestController(SubjectDtoService subjectDtoService, SubjectService subjectService) {
		this.subjectDtoService = subjectDtoService;
		this.subjectService = subjectService;
	}

	@GetMapping
	public List<SubjectDto> getSubjectsByDefaultLocale() {
		return subjectDtoService.getSubjectDtoList();
	}
	
	@GetMapping("/locale-id/{localeId}")
	public List<SubjectDto> getSubjectDtoListByLocale(@PathVariable("localeId") @LocaleExists Integer localeId) {
		return subjectDtoService.getSubjectDtoListByLocaleId(localeId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SubjectDto addSubject(@RequestBody @Validated(OnCreate.class) SubjectDto subjectDto) {
		Subject subject = subjectService.save(new Subject(subjectDto.getName().toLowerCase()));
		return convertToDto(subject);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess updateSubject(@PathVariable("id") @SubjectExists Integer subjectId,
									@RequestBody @Validated(OnUpdate.class) SubjectDto subjectDto) {
		Optional<Subject> subjectOpt = subjectService.findById(subjectId);
		Subject subject = subjectOpt.get();
		subject.setName(subjectDto.getName());
		subjectService.update(subject);
		return new ApiSuccess(new Date(), "You successfully edited subject!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSubject(@PathVariable("id") @SubjectExists Integer subjectId) {
		subjectService.deleteById(subjectId);
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully removed subject!"));
	}
	
	private SubjectDto convertToDto(Subject subject) {
		SubjectDto subjectDto = new SubjectDto();
		subjectDto.setId(subject.getId());
		subjectDto.setName(subject.getName());
		return subjectDto;
	}
}
