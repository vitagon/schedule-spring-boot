package com.vitgon.schedule.validator;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.vitgon.schedule.dto.UserDto;

public class ConstraintValidatorHelperTest {
	
	private class ClassWithGenericSuperClass<String> extends ArrayList<String> {
		private static final long serialVersionUID = 1L;
	}
	
	private class ClassWithGenericInterfaces implements Iterable<String> {

		@Override
		public Iterator<java.lang.String> iterator() {
			return null;
		}
	}
	
	private class ClassWithouGenericInterfaces {}

	@Test(expected = IllegalArgumentException.class)
	public void getPropertyValue_RequiredType_PropertyName_Instance__whenRequiredTypeIsNull_thenThrowException() {
		ConstraintValidatorHelper.getPropertyValue(null, "name", new Object());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPropertyValue_RequiredType_PropertyName_Instance__whenPropertyNameIsNull_thenThrowException() {
		ConstraintValidatorHelper.getPropertyValue(Object.class, null, new Object());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPropertyValue_RequiredType_PropertyName_Instance__whenInstanceIsNull_thenThrowException() {
		ConstraintValidatorHelper.getPropertyValue(Object.class, "name", null);
	}
	
//	@Test
//	public void getPropertyValue_RequiredType_PropertyName_Instance__whenRequiredTypeIsAssignableFromReadMethodReturnType_thenReturnValueIsNotNull() {
//		String name = ConstraintValidatorHelper.getPropertyValue(String.class, "name", new UserDto(1, "Matthew", null, null));
//		assertNotNull(name);
//	}
	
	@Test
	public void getPropertyValue_RequiredType_PropertyName_Instance__whenRequiredTypeIsNotAssignableFromReadMethodReturnType_thenReturnValueIsNull() {
		Object name = ConstraintValidatorHelper.getPropertyValue(Object.class, "name", new UserDto());
		assertNull(name);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getPropertyValue_RequiredType_PropertyName_Instance__whenPropertyNameDoesNotExistInInstance_thenThrowException() {
		ConstraintValidatorHelper.getPropertyValue(Object.class, "unknownProperty", new UserDto());
	}
	
	@Test
	public void isValid_PropertyValues_StringComparisonMode__whenComparisonModeIsNotEqualIgnoreCaseAndPropertyValuesAreEqual_thenReturnFalse() {
		List<String> propertyValues = new ArrayList<String>() {{ add("Mark"); add("mark"); }};
		boolean isValid = ConstraintValidatorHelper.isValid(propertyValues, StringComparisonMode.NOT_EQUAL_IGNORE_CASE);
		assertFalse(isValid);
	}
	
	@Test
	public void isValid_PropertyValues_StringComparisonMode__whenComparisonModeIsNotEqualIgnoreCaseAndPropertyValuesAreNotEqual_thenReturnTrue() {
		List<String> propertyValues = new ArrayList<String>() {{ add("Mark"); add("Bob"); }};
		boolean isValid = ConstraintValidatorHelper.isValid(propertyValues, StringComparisonMode.NOT_EQUAL_IGNORE_CASE);
		assertTrue(isValid);
	}
	
	@Test
	public void isValid_PropertyValues_StringComparisonMode__whenComparisonModeIsEqualIgnoreCaseAndPropertyValuesAreNotEqual_thenReturnFalse() {
		List<String> propertyValues = new ArrayList<String>() {{ add("Mark"); add("Bob");}};
		boolean isValid = ConstraintValidatorHelper.isValid(propertyValues, StringComparisonMode.EQUAL_IGNORE_CASE);
		assertFalse(isValid);
	}
	
	@Test
	public void isValid_PropertyValues_StringComparisonMode__whenComparisonModeIsEqualIgnoreCaseAndPropertyValuesAreEqual_thenReturnTrue() {
		List<String> propertyValues = new ArrayList<String>() {{ add("Mark"); add("mark");}};
		boolean isValid = ConstraintValidatorHelper.isValid(propertyValues, StringComparisonMode.EQUAL_IGNORE_CASE);
		assertTrue(isValid);
	}
	
	@Test
	public void isValid_PropertyValues_StringComparisonMode__whenComparisonModeIsEqualAndPropertyValuesAreNotEqual_thenReturnFalse() {
		List<String> propertyValues = new ArrayList<String>() {{ add("Mark"); add("mark"); }};
		boolean isValid = ConstraintValidatorHelper.isValid(propertyValues, StringComparisonMode.EQUAL);
		assertFalse(isValid);
	}
	
	@Test
	public void isValid_PropertyValues_StringComparisonMode__whenComparisonModeIsNotEqualAndPropertyValuesAreNotEqual_thenReturnTrue() {
		List<String> propertyValues = new ArrayList<String>() {{ add("Mark"); add("mark"); }};
		boolean isValid = ConstraintValidatorHelper.isValid(propertyValues, StringComparisonMode.NOT_EQUAL);
		assertTrue(isValid);
	}
	
	@Test
	public void resolveMessage_PropertyNamesArray_StringComparisonMode__whenComparisonModeIsEqualOrEqualIgnoreCase_thenAppendBeEqualToReturnString() {
		String[] propertyNames = {"email"};
		String errorResponseMessage = ConstraintValidatorHelper.resolveMessage(propertyNames, StringComparisonMode.EQUAL);
		assertThat(errorResponseMessage, containsString("must be equal"));
		assertThat(errorResponseMessage, endsWith("."));
	}
	
	@Test
	public void resolveMessage_PropertyNamesArray_StringComparisonMode__whenComparisonModeIsNotEqualOrNotEqualIgnoreCase_thenAppendNotBeEqualToReturnString() {
		String[] propertyNames = {"email"};
		String errorResponseMessage = ConstraintValidatorHelper.resolveMessage(propertyNames, StringComparisonMode.NOT_EQUAL);
		assertThat(errorResponseMessage, containsString("must not be equal"));
		assertThat(errorResponseMessage, endsWith("."));
	}
	
	@Test
	public void concatPropertyNames_PropertyNamesArray__success() throws Exception {
		String[] propertyNames = new String[] {"email"};
		Method method = Whitebox.getMethod(ConstraintValidatorHelper.class, "concatPropertyNames", String[].class);
		StringBuffer result = (StringBuffer) method.invoke(null, (Object) propertyNames);
		System.out.println(result.toString());
	}
	
	@Test
	public void capitalizeFirstLetter_Original__whenOriginalStringIsEmpty_thenReturnOriginalString() {
		String capitalizedStr = ConstraintValidatorHelper.capitalizeFirstLetter("");
		assertEquals(capitalizedStr, "");
	}
	
	@Test
	public void capitalizeFirstLetter_Original__whenOriginalStringIsNull_thenReturnNull() {
		String capitalizedStr = ConstraintValidatorHelper.capitalizeFirstLetter(null);
		assertNull(capitalizedStr);
	}
	
	@Test
	public void capitalizeFirstLetter_Original__whenOriginalStringIsNotNullAndIsNotEmpty_thenReturnCapitalizedString() {
		String capitalizedStr = ConstraintValidatorHelper.capitalizeFirstLetter("car");
		assertEquals("Car", capitalizedStr);
	}
	
	@Test
	public void getClassFromType_Type__whenClassNameEndPosIsFound_thenSuccess() {
		Type type = ConstraintValidatorHelper.class.getGenericSuperclass();
		Class<?> clazz = ConstraintValidatorHelper.getClassFromType(type);
		assertEquals(Object.class, clazz);
	}
	
	@Test
	public void getClassFromType_Type__whenClassNameEndPosIsNotFound_thenSuccess() {
		Type type = ClassWithGenericSuperClass.class.getGenericSuperclass();
		Class<?> clazz = ConstraintValidatorHelper.getClassFromType(type);
		assertEquals(ArrayList.class, clazz);
	}
	
	@Test
	public void getGenericTypeOfSuperInterface_FromClazz_InterfaceClazz_SequenceNumber__whenInterfaceClazzExistsOnFromClazzAndGenericTypeExists_thenReturnClassIsNotNull() {
		Class<?> genericClazz = ConstraintValidatorHelper.getGenericTypeOfSuperInterface(ClassWithGenericInterfaces.class, Iterable.class, 0);
		assertEquals(String.class, genericClazz);
	}
	
	@Test
	public void getGenericTypeOfSuperInterface_FromClazz_InterfaceClazz_SequenceNumber__whenInterfaceClazzExistsOnFromClazzAndGenericTypeDoesNotExists_thenNull() {
		Class<?> genericClazz = ConstraintValidatorHelper.getGenericTypeOfSuperInterface(ClassWithouGenericInterfaces.class, Object.class, 0);
		assertNull(genericClazz);
	}
	
}