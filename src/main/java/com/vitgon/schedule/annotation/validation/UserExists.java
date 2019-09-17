package com.vitgon.schedule.annotation.validation;

import com.vitgon.schedule.validator.UserExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserExistsValidator.class)
@Documented
public @interface UserExists {
	String message() default "{user.notFound}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
