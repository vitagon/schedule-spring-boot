package com.vitgon.schedule.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.annotation.validation.EnumMatch;

@RunWith(MockitoJUnitRunner.class)
public class EnumValidatorTest {
	
	@InjectMocks
	private EnumValidator enumValidator;
	
	private enum EnumWithNoElements {
	}
	
	private enum EnumWithTwoElements {
		ONE, TWO;
	}
	
	@Test
	public void initialize_EnumMatch__whenEnumHasZeroElements_thenSuccess() {
		enumValidator.initialize(initEnumMatchAnnotationWithNoEnumElements());
	}

	@Test
	public void initialize_EnumMatch__whenEnumHasMoreThanZeroElements_thenSuccess() {
		enumValidator.initialize(initEnumMatchAnnotationWithTwoEnumElements());
	}
	
	@Test
	public void isValid_EnumElement_ConstraintValidatorContext__whenEnumContainsProvidedEnumElement_thenReturnTrue() {
		enumValidator.initialize(initEnumMatchAnnotationWithTwoEnumElements());
		
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		boolean isValid = enumValidator.isValid("ONE", context);
		assertTrue(isValid);
	}
	
	@Test
	public void isValid_EnumElement_ConstraintValidatorContext__whenEnumDoesNotContainProvidedEnumElement_thenReturnFalse() {
		enumValidator.initialize(initEnumMatchAnnotationWithTwoEnumElements());
		
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		boolean isValid = enumValidator.isValid("THREE", context);
		assertFalse(isValid);
	}
	
	private EnumMatch initEnumMatchAnnotationWithNoEnumElements() {
		return new EnumMatch() {
			
			@Override
			public Class<? extends Enum> enumClazz() {
				return EnumWithNoElements.class;
			}

			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}

			@Override
			public String message() {
				return null;
			}

			@Override
			public Class<?>[] groups() {
				return null;
			}

			@Override
			public Class<? extends Payload>[] payload() {
				return null;
			}
		};
	}
	
	private EnumMatch initEnumMatchAnnotationWithTwoEnumElements() {
		return new EnumMatch() {
			
			@Override
			public Class<? extends Enum> enumClazz() {
				return EnumWithTwoElements.class;
			}

			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}

			@Override
			public String message() {
				return null;
			}

			@Override
			public Class<?>[] groups() {
				return null;
			}

			@Override
			public Class<? extends Payload>[] payload() {
				return null;
			}
		};
	}
}
