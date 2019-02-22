package com.vitgon.schedule.util;

public class StringUtil {
	
	public static String capitalizeFirstLetter(String word) {
		return word.substring(0,1).toUpperCase() + word.substring(1);
	}
	
	public static String applyUnderlyingStyle(String words) {
		return words.toLowerCase().replace(" ", "_");
	}
}
