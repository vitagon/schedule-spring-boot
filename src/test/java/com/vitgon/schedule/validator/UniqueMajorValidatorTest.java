package com.vitgon.schedule.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.MajorService;

@RunWith(MockitoJUnitRunner.class)
public class UniqueMajorValidatorTest {

	@Mock
	private MajorService majorService;
	
	@InjectMocks
	private UniqueMajorValidator uniqueMajorValidator;
	
	@Test
	public void isValid_MajorName_ConstraintValidatorContext__whenMajorIsNotFound_thenReturnTrue() {
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		when(majorService.findByName("banking")).thenReturn(null);
		
		boolean isValid = uniqueMajorValidator.isValid("banking", context);
		assertTrue(isValid);
	}
	
	@Test
	public void isValid_MajorName_ConstraintValidatorContext__whenMajorIsFound_thenReturnFalse() {
		ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
		when(majorService.findByName("banking")).thenReturn(Optional.of(new Major()));
		
		boolean isValid = uniqueMajorValidator.isValid("banking", context);
		assertFalse(isValid);
	}
}
