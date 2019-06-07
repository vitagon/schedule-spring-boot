package com.vitgon.schedule.annotation.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vitgon.schedule.validator.SubjectExistsValidator;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SubjectExistsValidator.class)
@Documented
public @interface SubjectExists {
	String message() default "{subject.notFound}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
