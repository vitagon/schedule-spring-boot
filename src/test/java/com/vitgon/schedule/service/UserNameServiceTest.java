package com.vitgon.schedule.service;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.translation.UserTranslation;
import com.vitgon.schedule.service.database.translation.UserTranslationService;


public class UserNameServiceTest {
	
	private UserTranslationService userTranslationService;
	private UserNameService userNameService;

	@Before
	public void init() {
		userTranslationService = Mockito.mock(UserTranslationService.class);
		userNameService = spy(new UserNameService(userTranslationService));
	}
	
	@Test
	public void testMakeupUsernameMethodWithUserAndLocale() throws Exception {
		Locale locale = new Locale("ru");
		User user = new User();
		user.setKeyFirstname("daniel");
		user.setKeyLastname("hanvel");
		when(userTranslationService.findByLocaleAndUser(locale, user)).thenReturn(null);
		
		String result = userNameService.makeupUsername(user, locale);
		
		Assert.assertNotNull(result);
		verify(userTranslationService, times(1)).findByLocaleAndUser(locale, user);
	}
	
	@Test
	public void testMakeupUsernameMethodWithNullUserAndLocale() throws Exception {
		Locale locale = new Locale("ru");
		String result = userNameService.makeupUsername(null, locale);
		
		Assert.assertNull(result);
		verify(userTranslationService, times(0)).findByLocaleAndUser(locale, null);
	}
	
	@Test
	public void testMakeupUsernameMethodWithUserAndEnglishLocale() throws Exception {
		Locale locale = new Locale("en");
		User user = new User();
		user.setKeyFirstname("daniel");
		user.setKeyLastname("hanvel");
		when(userNameService.makeupUsername(user)).thenReturn("testUsername");
		
		String result = userNameService.makeupUsername(user, locale);
		
		Assert.assertEquals("testUsername", result);
		verify(userTranslationService, times(0)).findByLocaleAndUser(locale, user);
	}
	
	@Test
	public void testMakeupUsernameMethodWithUserWithExistingTranslationAndLocale() throws Exception {
		Locale locale = new Locale("ru");
		User user = new User();
		user.setKeyFirstname("daniel");
		user.setKeyLastname("hanvel");
		UserTranslation translation = new UserTranslation();
		translation.setFirstname("даниель");
		translation.setLastname("ханвел");
		when(userTranslationService.findByLocaleAndUser(locale, user)).thenReturn(translation);
		
		String result = userNameService.makeupUsername(user, locale);
		
		Assert.assertEquals("Ханвел Даниель", result);
		verify(userTranslationService, times(1)).findByLocaleAndUser(locale, user);
	}
	
	@Test
	public void testMakeupUsernameMethodWithUser() throws Exception {
		User user = new User();
		user.setKeyFirstname("daniel");
		user.setKeyLastname("hanvel");
		user.setKeyMiddlename("Monbrick");
		
		String result = userNameService.makeupUsername(user);
		
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testMakeupUsernameMethodWithNullUser() throws Exception {
		String result = userNameService.makeupUsername(null);		
		Assert.assertNull(result);
	}
}
