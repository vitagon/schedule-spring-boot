package com.vitgon.schedule.annotation.validation;

import com.vitgon.schedule.validator.SubjectExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SubjectExistsValidator.class)
@Documented
public @interface SubjectExists {
	String message() default "{subject.notFound}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
