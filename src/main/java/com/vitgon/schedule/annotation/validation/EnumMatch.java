package com.vitgon.schedule.annotation.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.vitgon.schedule.validator.EnumValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumMatch {
	Class<? extends Enum> enumClazz();
	String message() default "Enum value was not found!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
