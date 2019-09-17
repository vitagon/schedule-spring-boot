package com.vitgon.schedule.annotation.validation;

import com.vitgon.schedule.validator.LocaleExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocaleExistsValidator.class)
@Documented
public @interface LocaleExists {
	String message() default "{locale.notFound}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
