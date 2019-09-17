package com.vitgon.schedule.validator;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class ConstraintValidatorHelper {
	
	public static <T> T getPropertyValue(Class<T> requiredType, String propertyName, Object instance) {
        if(requiredType == null) {
            throw new IllegalArgumentException("Invalid argument. requiredType must NOT be null!");
        }
        if(propertyName == null) {
            throw new IllegalArgumentException("Invalid argument. PropertyName must NOT be null!");
        }
        if(instance == null) {
            throw new IllegalArgumentException("Invalid argument. Object instance must NOT be null!");
        }
        T returnValue = null;
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, instance.getClass());
            Method readMethod = descriptor.getReadMethod();
            if(readMethod == null) {
                throw new IllegalStateException("Property '" + propertyName + "' of " + instance.getClass().getName() + " is NOT readable!");
            }
            if(requiredType.isAssignableFrom(readMethod.getReturnType())) {
                try {
                    Object propertyValue = readMethod.invoke(instance);
                    returnValue = requiredType.cast(propertyValue);
                } catch (Exception e) {
                    e.printStackTrace(); // unable to invoke readMethod
                }
            }
        } catch (IntrospectionException e) {
            throw new IllegalArgumentException("Property '" + propertyName + "' is NOT defined in " + instance.getClass().getName() + "!", e);
        }
        return returnValue; 
    }

    public static boolean isValid(Collection<String> propertyValues, StringComparisonMode comparisonMode) {
        boolean ignoreCase = false;
        switch (comparisonMode) {
	        case EQUAL_IGNORE_CASE:
	        case NOT_EQUAL_IGNORE_CASE:
	            ignoreCase = true;
        }

        List<String> values = new ArrayList<String> (propertyValues.size());
        for(String propertyValue : propertyValues) {
            if(ignoreCase) {
                values.add(propertyValue.toLowerCase());
            } else {
                values.add(propertyValue);
            }
        }

        switch (comparisonMode) {
	        case EQUAL:
	        case EQUAL_IGNORE_CASE:
	            Set<String> uniqueValues = new HashSet<String> (values);
	            return uniqueValues.size() == 1 ? true : false;
	        case NOT_EQUAL:
	        case NOT_EQUAL_IGNORE_CASE:
	            Set<String> allValues = new HashSet<String> (values);
	            return allValues.size() == values.size() ? true : false;
        }
		return true;
    }

    public static String resolveMessage(String[] propertyNames, StringComparisonMode comparisonMode) {
        StringBuffer buffer = concatPropertyNames(propertyNames);
        buffer.append(" must");
        switch(comparisonMode) {
	        case EQUAL:
	        case EQUAL_IGNORE_CASE:
	            buffer.append(" be equal");
	            break;
	        case NOT_EQUAL:
	        case NOT_EQUAL_IGNORE_CASE:
	            buffer.append(" not be equal");
	            break;
        }
        buffer.append('.');
        return buffer.toString();
    }

    private static StringBuffer concatPropertyNames(String[] propertyNames) {
        //TODO improve concating algorithm
        StringBuffer buffer = new StringBuffer();
        buffer.append('[');
        for(String propertyName : propertyNames) {
            char firstChar = Character.toUpperCase(propertyName.charAt(0));
            buffer.append(firstChar);
            buffer.append(propertyName.substring(1));
            buffer.append(", ");
        }
        buffer.delete(buffer.length()-2, buffer.length());
        buffer.append("]");
        return buffer;
    }
    
    public static String capitalizeFirstLetter(String original) {
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
	
	/**
	 * Method gets super interface {@code interfaceClazz} from class {@code fromClazz}
	 * and find generic argument type of interface on specific position {@code sequenceNumber}
	 * 
	 * @param fromClazz
	 * @param interfaceClazz
	 * @param sequenceNumber
	 * @return
	 */
	public static Class<?> getGenericTypeOfSuperInterface(Class<?> fromClazz, Class<?> interfaceClazz, int sequenceNumber) {
		Type[] genericInterfaces = fromClazz.getGenericInterfaces();
		Class<?> entityClazz = null;
		for (Type genericInterface : genericInterfaces) {
			Class<?> implementedInterface = ConstraintValidatorHelper.getClassFromType(genericInterface);
			if (implementedInterface.getName().equals(interfaceClazz.getName())) {
				Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
				entityClazz = (Class<?>) genericTypes[sequenceNumber];
			}
		}
		return entityClazz;
	}
}
