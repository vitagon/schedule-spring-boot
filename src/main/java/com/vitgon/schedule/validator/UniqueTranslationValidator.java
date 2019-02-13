package com.vitgon.schedule.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.annotation.validation.UniqueField;
import com.vitgon.schedule.annotation.validation.UniqueTranslation;
import com.vitgon.schedule.exception.GenericTypeNotFoundException;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.base.Service;


@Component
public class UniqueTranslationValidator implements ConstraintValidator<UniqueTranslation, Object> {
	
	private static final String BASE_SERVICE = "com.vitgon.schedule.service.base.Service";
	
	private ApplicationContext appContext;
	private LocaleService localeService;
	
	private UniqueField uniqueField;
	private String entityIdFieldName;
	private Class<?> entityService;

	@Autowired
	public UniqueTranslationValidator(ApplicationContext appContext, LocaleService localeService) {
		this.appContext = appContext;
		this.localeService = localeService;
	}

	@Override
	public void initialize(UniqueTranslation constraintAnnotation) {
		this.uniqueField = constraintAnnotation.uniqueField();
		this.entityIdFieldName = uniqueField.field();
		this.entityService = uniqueField.service();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		
		// get DTO field name that contains 'entity id'
		Method idFieldGetMethod = null;
		int entityId = 0;
		
		// get method that allows to get 'entity id' from DTO object
		try {
			idFieldGetMethod = object.getClass().getMethod("get" + capitalizeFirstLetter(entityIdFieldName));
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
		
		// get entity id
		try {
			entityId = (int) idFieldGetMethod.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// get entity service implementation
		Service serviceImpl = (Service) appContext.getBean(entityService);
		
		// get entity class from generic types of Service<Entity, ID> class
		Type[] genericInterfaces = entityService.getGenericInterfaces();
		Class<?> entityClazz = null;
		for (Type genericInterface : genericInterfaces) {
			Class<?> interfaceClass = getClassFromType(genericInterface);  
			if (interfaceClass.getName().equals(BASE_SERVICE)) {
				Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
				entityClazz = (Class<?>) genericTypes[0];
			}
		}
		
		
		if (entityClazz == null) {
			try {
				throw new GenericTypeNotFoundException("Generic entity on super interface 'Service' was not found!");
			} catch (GenericTypeNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		// find entity
		Object entity = serviceImpl.findById(entityId);
		
		// get locale id
		Method localeIdGetMethod = null;
		int localeId = 0;
		try {
			localeIdGetMethod = object.getClass().getMethod("get" + capitalizeFirstLetter("localeId"));
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			localeId = (int) localeIdGetMethod.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Locale locale = localeService.findById(localeId);
		
		// get entity translations | ex.: List<SubjectTranslation>
		Method translationsGetMethod = null;
		try {
			translationsGetMethod = entity.getClass().getMethod("getTranslations");
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object> translations = null;
		try {
			translations = (List<Object>) translationsGetMethod.invoke(entity);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		// check if translation for this entity with given locale exists
		boolean translationExists = false;
		for (Object translation : translations) {
			Object entityTranslationId = null;
			Locale translationLocale = null;
			
			// get getEntityTranslationId() method
			String entityName =  entityClazz.getSimpleName();
			Method entityTranslationIdGetMethod = null;
			try {
				entityTranslationIdGetMethod = translation.getClass().getMethod("get" + entityName + "TranslationId");
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			
			try {
				entityTranslationId = entityTranslationIdGetMethod.invoke(translation);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			// get getLocale() method of EntityTranslationId (it is compound PK, consists Locale and Entity)
			Method localeGetMethod = null;
			try {
				localeGetMethod = entityTranslationId.getClass().getMethod("getLocale");
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			
			// get locale of translation
			try {
				translationLocale = (Locale) localeGetMethod.invoke(entityTranslationId);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			} 
			
			// if locale from DTO equals to locale of entity translation, then exit 'for loop'
			// It means that translation of this entity for this locale is already exists and
			//   manager can't add translation
			if (locale == translationLocale) {
				translationExists = true;
				break;
			}
		}
		
		if (!translationExists) {
			return true;
		}
		
		return false;
	}
	
	public String capitalizeFirstLetter(String original) {
		if (original == null || original.length() == 0) {
			return original;
		}
		
		return original.substring(0,1).toUpperCase() + original.substring(1);
	}
	
	public static Class<?> getClassFromType(Type type) {
		String typeName = type.getTypeName();
		int classNameEndPos = typeName.indexOf("<");
		
		if (classNameEndPos == -1) {
			try {
				return Class.forName(typeName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		String className = typeName.substring(0, classNameEndPos);
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
