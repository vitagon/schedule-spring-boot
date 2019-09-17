package com.vitgon.schedule.annotation.validation;

import com.vitgon.schedule.validator.SchoolExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SchoolExistsValidator.class)
@Documented
public @interface SchoolExists {
	String message() default "{school.notFound}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
