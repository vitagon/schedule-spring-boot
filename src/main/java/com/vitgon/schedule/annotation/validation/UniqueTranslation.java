package com.vitgon.schedule.annotation.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vitgon.schedule.validator.UniqueTranslationValidator;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTranslationValidator.class)
public @interface UniqueTranslation {
	String message() default "This translation already exists!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	UniqueField uniqueField();
}
