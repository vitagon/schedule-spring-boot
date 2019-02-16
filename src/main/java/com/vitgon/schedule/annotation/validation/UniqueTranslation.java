package com.vitgon.schedule.annotation.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.base.Service;
import com.vitgon.schedule.validator.UniqueTranslationValidator;

/**
 * This annotation for searching if translation already exists.
 * Validator {@link com.vitgon.schedule.validator.UniqueTranslationValidator}
 * uses "localeId" and provided in uniqueField custom field to find
 * match in the database. If it finds match, then validation failed.
 * 
 * For getting locale object by "localeId" validator uses
 * localeService {@link com.vitgon.schedule.service.database.LocaleService}.
 * 
 * @author User
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTranslationValidator.class)
public @interface UniqueTranslation {
	String message() default "This translation already exists!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	UniqueField uniqueField();
	Locale locale() default @Locale(field = "localeId", service = LocaleService.class);
	TranslationEntity translationEntity();
}
