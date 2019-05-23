package com.vitgon.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorDto {
	private Integer id;
	private String name;
	private String url;
	private String translation;
}
