package com.vitgon.schedule.dto;

public class LocaleDto {
	private int id;
	private String code;
	private boolean active;
	
	public LocaleDto() {
	}
	
	public LocaleDto(int id, String code, boolean active) {
		super();
		this.id = id;
		this.code = code;
		this.active = active;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
