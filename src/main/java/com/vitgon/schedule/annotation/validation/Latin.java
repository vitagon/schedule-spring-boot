package com.vitgon.schedule.annotation.validation;

import com.vitgon.schedule.validator.LatinValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LatinValidator.class)
@Documented
public @interface Latin {
	String message() default "";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
