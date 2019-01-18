package com.vitgon.schedule.pojo.school;

import java.util.List;

import lombok.Data;

@Data
public class SchoolPojo {
	private int id;
	private String title;
	private String url;
	private List<MajorPojo> majors;
}
