package com.vitgon.schedule.dto;

import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.UserExists;
import com.vitgon.schedule.group.OnCheck;
import com.vitgon.schedule.group.OnDelete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTranslationDto {
	
	@UserExists(groups = {Default.class, OnCheck.class, OnDelete.class})
	private Integer userId;
	
	@LocaleExists(groups = {Default.class, OnCheck.class, OnDelete.class})
	private Integer localeId;
	
	@Size(min = 3, max = 50, message = "{Size.default}")
	private String firstname;
	
	@Size(min = 3, max = 50, message = "{Size.default}")
	private String lastname;
	
	@Size(min = 3, max = 50, message = "{Size.default}")
	private String middlename;
}
