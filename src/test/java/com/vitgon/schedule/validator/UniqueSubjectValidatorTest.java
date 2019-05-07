package com.vitgon.schedule.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.database.SubjectService;

@RunWith(MockitoJUnitRunner.class)
public class UniqueSubjectValidatorTest {

	@Mock
	private SubjectService subjectService;
	
	@InjectMocks
	private UniqueSubjectValidator uniqueSubjectValidator;
	
	@Test
	public void isValid_Value_ConstraintValidatorContext__whenSubjectIsNotFound_thenReturnTrue() {
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		when(subjectService.findByName("math")).thenReturn(null);
		
		boolean isValid = uniqueSubjectValidator.isValid("math", context);
		assertTrue(isValid);
	}
	
	@Test
	public void isValid_Value_ConstraintValidatorContext__whenSubjectIsFound_thenReturnFalse() {
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		when(subjectService.findByName("math")).thenReturn(new Subject());
		
		boolean isValid = uniqueSubjectValidator.isValid("math", context);
		assertFalse(isValid);
	}
}
