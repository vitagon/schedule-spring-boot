package com.vitgon.schedule.service;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.auth.User;
import com.vitgon.schedule.model.database.translation.UserTranslation;
import com.vitgon.schedule.resolver.UrlLocaleResolver;
import com.vitgon.schedule.service.database.translation.UserTranslationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserNameService {
	
	private UserTranslationService userTranslationService;

	/**
	 * Make up user name
	 * Will return ex.: Jordan Daniel
	 * 
	 * @return combined firstname, lastname and middlename with capitalized first letter
	 */
	public String makeupUsername(User user, Locale locale) {
		if (user == null) {
			return null;
		}
		
		if (locale.getCode().equals(UrlLocaleResolver.EN)) {
			return makeupUsername(user);
		}
		
		UserTranslation userTranslation = userTranslationService.findByLocaleAndUser(locale, user);
		String lastname = null, firstname = null, middlename = null;
		
		if (userTranslation == null) {
			lastname = user.getKeyLastname();
			firstname = user.getKeyFirstname();
			middlename = user.getKeyMiddlename();
		} else {
			lastname = userTranslation.getLastname();
			firstname = userTranslation.getFirstname();
			middlename = userTranslation.getMiddlename();
		}

		return makeUp(lastname, firstname, middlename);
	}
	
	public String makeupUsername(User user) {
		if (user == null) {
			return null;
		}
		
		String lastname = null, firstname = null, middlename = null;
		
		lastname = user.getKeyLastname();
		firstname = user.getKeyFirstname();
		middlename = user.getKeyMiddlename();
		
		return makeUp(lastname, firstname, middlename);
	}
	
	private String makeUp(String lastname, String firstname, String middlename) {
		StringBuilder nameStringBuilder = new StringBuilder()
				.append(capitalizeFirstLetter(lastname))
				.append(" ")
				.append(capitalizeFirstLetter(firstname));
		
		if (middlename != null) {
			nameStringBuilder
				.append(" ")
				.append(capitalizeFirstLetter(middlename));
		}

		return nameStringBuilder.toString();
	}
	
	private String capitalizeFirstLetter(String word) {
		return word.substring(0,1).toUpperCase() + word.substring(1);
	}
}
