package com.vitgon.schedule.controller.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.controller.rest.advice.FieldValidationException;
import com.vitgon.schedule.dto.UserDto;
import com.vitgon.schedule.dto.UserTranslationDto;
import com.vitgon.schedule.group.OnCheck;
import com.vitgon.schedule.group.OnDelete;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.translation.UserTranslation;
import com.vitgon.schedule.model.database.translation.pk.UserTranslationId;
import com.vitgon.schedule.projection.UserProjection;
import com.vitgon.schedule.service.UserDtoService;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.service.database.translation.UserTranslationService;
import com.vitgon.schedule.validator.LocaleExistsValidator;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/translations")
public class UserRestController {
	
	private UserTranslationService userTranslationService;
	private UserService userService;
	private UserDtoService userDtoService;
	private LocaleService localeService;

	public UserRestController(UserTranslationService userTranslationService, UserService userService, UserDtoService userDtoService, LocaleService localeService) {
		this.userTranslationService = userTranslationService;
		this.userService = userService;
		this.userDtoService = userDtoService;
		this.localeService = localeService;
	}

	@GetMapping("/users/role/{role}")
	public List<UserDto> getAllByRole(
			@PathVariable String role,
			@PageableDefault(page=0, size = 10, sort = {"id"}, direction = Direction.ASC) Pageable pageable) {
		return userDtoService.getUserDtoListByRole(role, pageable);
	}
	
	@GetMapping("/users/role/{role}/locale-id/{localeId}")
	public List<UserDto> getAllByLocaleAndRole(
			@PathVariable String role,
			@PathVariable @LocaleExists Integer localeId,
			@PageableDefault(page=0, size = 10, sort = {"id"}, direction = Direction.ASC) Pageable pageable) {
		return userDtoService.getUserDtoListByRoleAndLocaleId(localeId, role, pageable);
	}
	
	@GetMapping("/users/{userId}")
	public UserProjection getUserTranslationByUserId(@Validated(OnCheck.class) UserTranslationDto userTranslationDto) {
		return  userTranslationService.getUserTranslationProjectionByLocaleIdAndUserId(
				userTranslationDto.getLocaleId(), userTranslationDto.getUserId()
		);
	}
	
	@PostMapping("/users")
	public UserTranslation createTranslation(@RequestBody @Valid UserTranslationDto userTranslationDto) throws FieldValidationException {
		Optional<User> userOpt = userService.findById(userTranslationDto.getUserId());
		Optional<Locale> localeOpt = localeService.findById(userTranslationDto.getLocaleId());
		
		Optional<UserTranslation> userTransl = userTranslationService.findById(new UserTranslationId(userOpt.get(), localeOpt.get()));
		if (userTransl.isPresent()) {
			throw new FieldValidationException("localeId", "Duplicate.translation");
		}
		
		UserTranslation userTranslation = new UserTranslation(
				new UserTranslationId(userOpt.get(), localeOpt.get()),
				userTranslationDto.getLastname(),
				userTranslationDto.getFirstname(),
				userTranslationDto.getMiddlename());
		userTranslation = userTranslationService.save(userTranslation);
		
		return userTranslation;
	}
	
	@PutMapping("/users")
	public UserTranslation updateTranslation(@RequestBody @Valid UserTranslationDto userTranslationDto) throws FieldValidationException {
		Optional<User> userOpt = userService.findById(userTranslationDto.getUserId());
		Optional<Locale> localeOpt = localeService.findById(userTranslationDto.getLocaleId());
		
		Optional<UserTranslation> userTranslOpt = userTranslationService.findById(new UserTranslationId(userOpt.get(), localeOpt.get()));
		if (!userTranslOpt.isPresent()) {
			throw new FieldValidationException("localeId", "translation.notFound");
		}
		
		UserTranslation userTransl = userTranslOpt.get();
		userTransl.setFirstname(userTranslationDto.getFirstname());
		userTransl.setLastname(userTranslationDto.getLastname());
		userTransl.setMiddlename(userTranslationDto.getMiddlename());
		userTransl = userTranslationService.save(userTransl);
		
		return userTransl;
	}
	
	@GetMapping("/users/check")
	public void checkIfTranslationExists(@RequestParam("exists") @NotNull Boolean shouldExists,
										 @Validated(OnCheck.class) UserTranslationDto userTranslationDto) throws FieldValidationException {
		Optional<UserTranslation> userTransl = userTranslationService.findByLocaleIdAndUserId(
				userTranslationDto.getLocaleId(),
				userTranslationDto.getUserId()
		);
		if (userTransl.isPresent() && shouldExists == false) {
			throw new FieldValidationException("localeId", "Duplicate.translation");
		} else if (!userTransl.isPresent() && shouldExists == true) {
			throw new FieldValidationException("localeId", "translation.notFound");
		}
	}
	
	@DeleteMapping("/users/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public void removeUserTranslation(@RequestBody @Validated(OnDelete.class) UserTranslationDto userTranslationDto) throws FieldValidationException {
		Optional<UserTranslation>  userTranslationOpt = userTranslationService.findByLocaleIdAndUserId(
				userTranslationDto.getLocaleId(), userTranslationDto.getUserId()
		);
		
		if (!userTranslationOpt.isPresent()) {
			throw new FieldValidationException("localeId", "translation.notFound");
		}
		
		userTranslationService.delete(userTranslationOpt.get());
	}
}
