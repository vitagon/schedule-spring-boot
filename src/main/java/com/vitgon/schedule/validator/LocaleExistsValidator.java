package com.vitgon.schedule.validator;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
public class LocaleExistsValidator implements ConstraintValidator<LocaleExists, Integer> {
	
	private LocaleService localeService;

	public LocaleExistsValidator(LocaleService localeService) {
		this.localeService = localeService;
	}

	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		Optional<Locale> locale = localeService.findById(id);
		if (locale.isPresent()) {
			return true;
		}
		return false;
	}
}
