package com.vitgon.schedule.service.database.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.vitgon.schedule.model.HibernateSequence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class HibernateSequenceService {
	
	private EntityManager entityManager;
	
	public Integer getNextVal() {
		Query query = entityManager.createQuery("select nextval('hibernate_sequence')", HibernateSequence.class);
		// TODO: get nextval
		HibernateSequence hibSequence = (HibernateSequence) query.getSingleResult();
		return hibSequence.getNextval();
	}
}
