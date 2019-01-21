package com.vitgon.schedule.pojo;

import java.util.List;

import lombok.Data;

@Data
public class SchoolPOJO {
	private int id;
	private String title;
	private String url;
	private List<MajorPOJO> majors;
}
