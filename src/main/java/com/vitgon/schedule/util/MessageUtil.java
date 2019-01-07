package com.vitgon.schedule.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Properties;

public class MessageUtil {
	
	private static final String MESSAGES_LOCATION = "i18n"; 
	
	public static String getAttribute(String attribName, Locale locale) {
		
		String resourcePath =  MESSAGES_LOCATION + File.separator + getMessageFilename(locale);
		System.out.println(resourcePath);
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(resourcePath)) {
		    props.load(new InputStreamReader(resourceStream, Charset.forName("UTF-8")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props.getProperty(attribName);
	}
	
	public static String getMessageFilename(Locale locale) {
		String lang = locale.getLanguage();
		return new StringBuilder()
				.append("messages_")
				.append(lang)
				.append(".properties")
				.toString();
	}
}
