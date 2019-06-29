package com.vitgon.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoAdminPanel {
	private Integer id;
	private String firstname;
	private String lastname;
	private String middlename;
	private String fullName;
	
	private String firstnameTranslation;
	private String lastnameTranslation;
	private String middlenameTranslation;
	private String fullNameTranslation;
}
