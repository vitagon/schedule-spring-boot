package com.vitgon.schedule.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.annotation.validation.TranslationEntity;
import com.vitgon.schedule.annotation.validation.UniqueField;
import com.vitgon.schedule.annotation.validation.UniqueTranslation;
import com.vitgon.schedule.exception.GenericTypeNotFoundException;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.base.Service;


@Component
public class UniqueTranslationValidator implements ConstraintValidator<UniqueTranslation, Object> {
	
	private static final Class<?> BASE_SERVICE = Service.class;
	private String errorMessage;
	
	private ApplicationContext appContext;
	private Service localeService;
	private Service entityService;
	private Service translationEntityService;
	
	private UniqueField uniqueField;
	private TranslationEntity translationEntityAnnotation;
	private String entityIdFieldName;
	private String localeIdFieldName;
	

	@Autowired
	public UniqueTranslationValidator(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	@Override
	public void initialize(UniqueTranslation constraintAnnotation) {
		this.errorMessage = constraintAnnotation.message();
		
		this.uniqueField = constraintAnnotation.uniqueField();
		this.entityIdFieldName = uniqueField.field();
		this.entityService = (Service) appContext.getBean(uniqueField.service());
		
		com.vitgon.schedule.annotation.validation.Locale localeAnnotation = constraintAnnotation.locale();
		this.localeIdFieldName = localeAnnotation.field();
		this.localeService = (Service) appContext.getBean(localeAnnotation.service());
		
		translationEntityAnnotation = constraintAnnotation.translationEntity();
		this.translationEntityService = appContext.getBean(translationEntityAnnotation.service());
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		// disable default violation and bind error with <select th:field="*{localeId}">
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(errorMessage)
			   .addPropertyNode(localeIdFieldName)
			   .addConstraintViolation();
		
		// get 'entity id' from DTO field
		int entityId = ConstraintValidatorHelper.getPropertyValue(Integer.class, entityIdFieldName, object);

		// get entity class from generic types of Service<Entity, ID> class
		Class<?> entityClazz = ConstraintValidatorHelper.getGenericTypeOfSuperInterface(uniqueField.service(), BASE_SERVICE, 0);
		
		if (entityClazz == null) {
			try {
				throw new GenericTypeNotFoundException("Generic entity was not found on super interface 'Service' !");
			} catch (GenericTypeNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		// find entity
		Object entity = entityService.findById(entityId);
		
		// get locale id from client and get locale object from database
		int localeId = ConstraintValidatorHelper.getPropertyValue(Integer.class, localeIdFieldName, object);
		Locale locale = (Locale) localeService.findById(localeId);
		
		// check if translation for this entity with given locale exists
		Class<?> translationIdClazz = ConstraintValidatorHelper.getGenericTypeOfSuperInterface(translationEntityAnnotation.service(), BASE_SERVICE, 1);
		Object translationId = null;
		try {
			translationId = translationIdClazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			PropertyUtils.setProperty(translationId, translationEntityAnnotation.localeField(), locale);
			PropertyUtils.setProperty(translationId, translationEntityAnnotation.entityField(), entity);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		Object translationRecord = translationEntityService.findById(translationId);
		
		if (translationRecord == null) {
			return true;
		}
		
		return false;
	}
}
