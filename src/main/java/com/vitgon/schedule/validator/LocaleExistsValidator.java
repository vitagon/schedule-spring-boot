package com.vitgon.schedule.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class LocaleExistsValidator implements ConstraintValidator<LocaleExists, Integer> {
	
	private LocaleService localeService;

	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		Optional<Locale> locale = localeService.findById(id);
		if (locale.isPresent()) {
			return true;
		}
		return false;
	}
}
