package com.vitgon.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorDtoControl {
	
	private int id;
	private String name;
	private String translation;
	private String url;
	
	public MajorDtoControl(Integer id, String name, String translation) {
		this.id = id;
		this.name = name;
		this.translation = translation;
	}
}