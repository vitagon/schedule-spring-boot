package com.vitgon.schedule.controller.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.annotation.validation.GroupExists;
import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.controller.rest.advice.FieldValidationException;
import com.vitgon.schedule.dto.GroupTranslationDto;
import com.vitgon.schedule.model.database.translation.GroupTranslation;
import com.vitgon.schedule.service.database.translation.GroupTranslationService;

@RestController
@RequestMapping("/api/translations/groups")
public class GroupTranslationRestController {
	
	private GroupTranslationService groupTranslService;

	public GroupTranslationRestController(GroupTranslationService groupTranslService) {
		this.groupTranslService = groupTranslService;
	}

	@GetMapping("/{groupId}/locales/{localeId}")
	public GroupTranslationDto getGroupTranslation(@PathVariable @LocaleExists Integer localeId,
												   @PathVariable @GroupExists Integer groupId) throws FieldValidationException {
		Optional<GroupTranslation> groupTranslationOpt = groupTranslService.findByLocaleIdAndGroupId(localeId, groupId);
		if (!groupTranslationOpt.isPresent()) {
			throw new FieldValidationException("localeId", "translation.notFound");
		}
		
		GroupTranslationDto groupTranslationDto = new GroupTranslationDto();
		groupTranslationDto.setGroupId(groupId);
		groupTranslationDto.setLocaleId(localeId);
		groupTranslationDto.setTranslation(groupTranslationOpt.get().getTranslation());
		return groupTranslationDto;
	}
	
	@PostMapping("/{groupId}/locales/{localeId}")
	public GroupTranslationDto addGroupTranslation(@RequestBody @Valid GroupTranslationDto groupTranslationDto) throws FieldValidationException {
		Optional<GroupTranslation> groupTranslationOpt = groupTranslService.findByLocaleIdAndGroupId(
				groupTranslationDto.getLocaleId(), groupTranslationDto.getGroupId());
		if (!groupTranslationOpt.isPresent()) {
			groupTranslService.save(groupTranslationDto.getGroupId(), groupTranslationDto.getLocaleId(), groupTranslationDto.getTranslation());
		} else {
			throw new FieldValidationException("translation", "Duplicate.translation");
		}
		
		return groupTranslationDto;
	}
	
	@PutMapping("/{groupId}/locales/{localeId}")
	public void editGroupTranslation(@RequestBody @Valid GroupTranslationDto groupTranslationDto) throws FieldValidationException {
		Optional<GroupTranslation> groupTranslationOpt = groupTranslService.findByLocaleIdAndGroupId(
				groupTranslationDto.getLocaleId(), groupTranslationDto.getGroupId());
		
		if (groupTranslationOpt.isPresent()) {
			GroupTranslation groupTranslation = groupTranslationOpt.get();
			groupTranslation.setTranslation(groupTranslationDto.getTranslation());
			groupTranslService.save(groupTranslation);
			return;
		}
		
		throw new FieldValidationException("localeId", "translation.notFound");
	}
	
	@DeleteMapping("/{groupId}/locales/{localeId}")
	public void deleteGroupTranslation(@PathVariable("groupId") @GroupExists Integer groupId,
            						   @PathVariable("localeId") @LocaleExists Integer localeId) throws FieldValidationException {
		Optional<GroupTranslation> groupTranslationOpt = groupTranslService.findByLocaleIdAndGroupId(localeId, groupId);
		
		if (groupTranslationOpt.isPresent()) {
			GroupTranslation groupTranslation = groupTranslationOpt.get();
			groupTranslService.delete(groupTranslation);
			return;
		}
		
		throw new FieldValidationException("localeId", "translation.notFound");
	}
}