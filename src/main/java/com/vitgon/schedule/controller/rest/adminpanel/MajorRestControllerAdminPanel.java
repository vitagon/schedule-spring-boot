package com.vitgon.schedule.controller.rest.adminpanel;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.MajorExists;
import com.vitgon.schedule.dto.AddMajorDto;
import com.vitgon.schedule.dto.EditMajorDto;
import com.vitgon.schedule.dto.MajorDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.MajorDtoService;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.database.MajorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/control/majors")
public class MajorRestControllerAdminPanel {
	
	private MajorService majorService;
	private MessageService messageService;
	private MajorDtoService majorDtoService;

	public MajorRestControllerAdminPanel(MajorService majorService, MessageService messageService, MajorDtoService majorDtoService) {
		this.majorService = majorService;
		this.messageService = messageService;
		this.majorDtoService = majorDtoService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MajorDto addMajor(@RequestBody @Valid AddMajorDto addMajorDto) {
		Major major = majorDtoService.convertToEntity(addMajorDto);
		major = majorService.save(major);
		return majorDtoService.getMajorById(major.getId());
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess updateMajor(@RequestBody @Valid EditMajorDto editMajorDto) {
		Optional<Major> majorOpt = majorService.findById(editMajorDto.getId());
		if (!majorOpt.isPresent()) {
			throw new IllegalArgumentException(String.format("Major with id=%d name doesn't exist!", editMajorDto.getId()));
		}
		Major major = majorDtoService.convertToEntity(editMajorDto, majorOpt.get());
		majorService.update(major);
		return new ApiSuccess(new Date(), "You successfully updated major!");
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteMajor(@RequestParam("id") Major major) {
		if (major == null) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("id", Arrays.asList(messageService.getMessage("chooseValue")));
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ApiError(new Date(), "Major Not Found", errors));
		}
		majorService.delete(major);
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully removed major!"));
	}
	
	@GetMapping("/locale-id/{localeId}")
	@ResponseStatus(HttpStatus.OK)
	public List<MajorDto> getMajors(@PathVariable("localeId") @LocaleExists Integer localeId) {
		return majorDtoService.getAllByLocaleIdForAdminPanel(localeId);
	}

	@GetMapping("{id}")
	public MajorDto getMajor(@PathVariable("id") @MajorExists Integer majorId) {
		return majorDtoService.getMajorById(majorId);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<MajorDto> getMajors() {
		return majorDtoService.getAllByLocaleIdForAdminPanel();
	}
}
