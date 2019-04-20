package com.vitgon.schedule.service.database.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Service
public class HibernateSequenceService {
	
	private EntityManager entityManager;
	
	@Data
	private class HibernateSequence {
		private Integer nextval;
	}
	
	public Integer getNextVal() {
		Query query = entityManager.createQuery("select nextval('hibernate_sequence')", HibernateSequence.class);
		HibernateSequence hibSequence = (HibernateSequence) query.getSingleResult();
		return hibSequence.getNextval();
	}
}
