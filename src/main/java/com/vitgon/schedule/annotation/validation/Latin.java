package com.vitgon.schedule.annotation.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vitgon.schedule.validator.LatinValidator;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LatinValidator.class)
@Documented
public @interface Latin {
	String message() default "";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
