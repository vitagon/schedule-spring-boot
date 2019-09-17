package com.vitgon.schedule.annotation.validation;

import com.vitgon.schedule.validator.UniqueSubjectValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueSubjectValidator.class)
public @interface UniqueSubject {
	String message() default "";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {}; 
}
