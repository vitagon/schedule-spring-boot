package com.vitgon.schedule.dto;

public class GroupDto {
	private int id;
	private String name;
	private String nameTranslation;

	private Integer number;
	private Integer courseNum;
	private String suffix;
	private String suffixTranslation;

	private String degree;
	private String degreeTranslation;
	
	public GroupDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

    public GroupDto() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameTranslation() {
		return nameTranslation;
	}

	public void setNameTranslation(String nameTranslation) {
		this.nameTranslation = nameTranslation;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}

	public String getSuffixTranslation() {
		return suffixTranslation;
	}

	public void setSuffixTranslation(String suffixTranslation) {
		this.suffixTranslation = suffixTranslation;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegreeTranslation() {
		return degreeTranslation;
	}

	public void setDegreeTranslation(String degreeTranslation) {
		this.degreeTranslation = degreeTranslation;
	}
}
