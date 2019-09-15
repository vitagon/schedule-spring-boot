package com.vitgon.schedule.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class HibernateSequence {
	private Integer nextval;

	public HibernateSequence(Integer nextval) {
		super();
		this.nextval = nextval;
	}

	public Integer getNextval() {
		return nextval;
	}

	public void setNextval(Integer nextval) {
		this.nextval = nextval;
	}
}
