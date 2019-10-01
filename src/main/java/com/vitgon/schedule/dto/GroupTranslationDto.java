package com.vitgon.schedule.dto;

import javax.validation.constraints.Size;

import com.vitgon.schedule.annotation.validation.GroupExists;
import com.vitgon.schedule.annotation.validation.LocaleExists;

public class GroupTranslationDto {
	
	@GroupExists
	private Integer groupId;
	@LocaleExists
	private Integer localeId;
	
	@Size(min = 3, message = "{Size.default}")
	private String translation;
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getLocaleId() {
		return localeId;
	}
	public void setLocaleId(Integer localeId) {
		this.localeId = localeId;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
}
