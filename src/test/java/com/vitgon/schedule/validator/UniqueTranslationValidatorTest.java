package com.vitgon.schedule.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;
import javax.validation.Payload;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationContext;

import com.vitgon.schedule.annotation.validation.Locale;
import com.vitgon.schedule.annotation.validation.TranslationEntity;
import com.vitgon.schedule.annotation.validation.UniqueField;
import com.vitgon.schedule.annotation.validation.UniqueTranslation;
import com.vitgon.schedule.dto.AddTeacherTranslationDto;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.translation.UserTranslation;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.UserService;
import com.vitgon.schedule.service.database.base.Service;
import com.vitgon.schedule.service.database.translation.UserTranslationService;

@RunWith(MockitoJUnitRunner.class)
public class UniqueTranslationValidatorTest {
	
	@Mock
	private ApplicationContext appContext;
	
	@InjectMocks
	private UniqueTranslationValidator uniqueTranslationValidator;
	
	private Service<?, Integer> entityService;
	private Service<?, Integer> localeService;
	private Service<?, Object> translationEntityService;
	
	private UniqueField uniqueField;
	private Locale localeAnnotation;
	private TranslationEntity translationEntityAnnotation;
	
	@Before
	public void init() {
		UniqueTranslation uniqueTranslation = initUniqueTranslationAnnotation();
		uniqueField = uniqueTranslation.uniqueField();
		localeAnnotation = uniqueTranslation.locale();
		translationEntityAnnotation = uniqueTranslation.translationEntity();
		
		entityService = mock(Service.class);
		localeService = mock(Service.class);
		translationEntityService = mock(Service.class);
		
		// set entity service mock bean
		Answer<Service<?, Integer>> answerForEntityService = (InvocationOnMock invocation) -> entityService;
		when(appContext.getBean(uniqueField.service())).thenAnswer(answerForEntityService);
		
		// set locale service mock bean
		Answer<Service<?, Integer>> answerForLocaleService = (InvocationOnMock invocation) -> localeService;
		when(appContext.getBean(localeAnnotation.service())).thenAnswer(answerForLocaleService);
		
		// set translationEntity service mock bean
		Answer<Service<?, Object>> answerForTranslationEntityService = (InvocationOnMock invocation) -> translationEntityService;
		when(appContext.getBean(translationEntityAnnotation.service())).thenAnswer(answerForTranslationEntityService);
		
		uniqueTranslationValidator.initialize(uniqueTranslation);
	}
	
	@Test
	public void isValid_Object_ConstraintValidatorContext__whenTranslationRecordDoesNotExist_thenReturnTrue() throws Exception {
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		AddTeacherTranslationDto dtoObject = new AddTeacherTranslationDto(); 
		dtoObject.setUserId(152);
		dtoObject.setLocaleId(108);
		
		ConstraintViolationBuilder constraintBuilder = mock(ConstraintViolationBuilder.class);
		NodeBuilderCustomizableContext customizableContext = mock(NodeBuilderCustomizableContext.class);
		when(context.buildConstraintViolationWithTemplate(any()))
			.thenReturn(constraintBuilder);
		when(constraintBuilder.addPropertyNode(any())).thenReturn(customizableContext);
		
		Answer<Object> answerForEntity = (InvocationOnMock invocation) -> new User();
		when(entityService.findById(152)).thenAnswer(answerForEntity);
		
		Answer<Object> answerForLocale = (InvocationOnMock invocation) -> {
			return new com.vitgon.schedule.model.database.Locale();
		};
		when(localeService.findById(108)).thenAnswer(answerForLocale);
		
		when(translationEntityService.findById(any())).thenReturn(null);
		boolean result = uniqueTranslationValidator.isValid(dtoObject, context);
		assertTrue(result);
	}
	
	@Test
	public void isValid_Object_ConstraintValidatorContext__whenTranslationRecordExists_thenReturnFalse() throws Exception {
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		AddTeacherTranslationDto dtoObject = new AddTeacherTranslationDto(); 
		dtoObject.setUserId(152);
		dtoObject.setLocaleId(108);
		
		ConstraintViolationBuilder constraintBuilder = mock(ConstraintViolationBuilder.class);
		NodeBuilderCustomizableContext customizableContext = mock(NodeBuilderCustomizableContext.class);
		when(context.buildConstraintViolationWithTemplate(any()))
			.thenReturn(constraintBuilder);
		when(constraintBuilder.addPropertyNode(any())).thenReturn(customizableContext);
		
		Answer<Object> answerForEntity = (InvocationOnMock invocation) -> new User();
		when(entityService.findById(152)).thenAnswer(answerForEntity);
		
		Answer<Object> answerForLocale = (InvocationOnMock invocation) -> {
			return new com.vitgon.schedule.model.database.Locale();
		};
		when(localeService.findById(108)).thenAnswer(answerForLocale);
		
		Answer<Object> answerForTranslation = (InvocationOnMock invocation) -> new UserTranslation();
		when(translationEntityService.findById(any())).thenAnswer(answerForTranslation);
		
		boolean result = uniqueTranslationValidator.isValid(dtoObject, context);
		assertFalse(result);
	}
	
	private UniqueTranslation initUniqueTranslationAnnotation() {
		return new UniqueTranslation() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return UniqueTranslation.class;
			}

			@Override
			public String message() {
				return "This translation already exists!";
			}

			@Override
			public Class<?>[] groups() {
				return new Class<?>[] {};
			}

			@Override
			public Class<? extends Payload>[] payload() {
				return new Class[] {};
			}

			@Override
			public UniqueField uniqueField() {
				return new UniqueField() {
					
					@Override
					public Class<? extends Annotation> annotationType() {
						return UniqueField.class;
					}
					
					@Override
					public Class<?> service() {
						return UserService.class;
					}
					
					@Override
					public String field() {
						return "userId";
					}
				};
			}

			@Override
			public Locale locale() {
				return new Locale() {
					
					@Override
					public Class<? extends Annotation> annotationType() {
						return Locale.class;
					}
					
					@Override
					public Class<?> service() {
						return LocaleService.class;
					}
					
					@Override
					public String field() {
						return "localeId";
					}
				};
			}

			@Override
			public TranslationEntity translationEntity() {
				return new TranslationEntity() {
					
					@Override
					public Class<? extends Annotation> annotationType() {
						return TranslationEntity.class;
					}
					
					@Override
					public Class<? extends Service> service() {
						return UserTranslationService.class;
					}
					
					@Override
					public String localeField() {
						return "locale";
					}
					
					@Override
					public String entityField() {
						return "user";
					}
				};
			}
		};
	}
}