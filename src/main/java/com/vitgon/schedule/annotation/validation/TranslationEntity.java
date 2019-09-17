package com.vitgon.schedule.annotation.validation;

import com.vitgon.schedule.service.database.base.Service;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TranslationEntity {
	String entityField();
	String localeField() default "locale";
	Class<? extends Service> service();
}
