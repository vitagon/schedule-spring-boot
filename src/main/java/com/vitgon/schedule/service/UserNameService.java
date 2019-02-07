package com.vitgon.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.model.translation.UserTranslation;
import com.vitgon.schedule.service.database.translation.UserTranslationService;

@Component
public class UserNameService {
	
	private UserTranslationService userTranslationService;
	
	@Autowired
	public UserNameService(UserTranslationService userTranslationService) {
		this.userTranslationService = userTranslationService;
	}

	/**
	 * Make up user name | Combine firstname, lastname and middlename
	 * Will return ex.:"Jordan Daniel"
	 * 
	 * @return 
	 */
	public String makeupUsername(User user, Locale locale) {
		if (user == null) {
			return null;
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
	
	public String makeUp(String lastname, String firstname, String middlename) {
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
	
	public String capitalizeFirstLetter(String word) {
		return word.substring(0,1).toUpperCase() + word.substring(1);
	}
}
