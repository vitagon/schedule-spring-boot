package com.vitgon.schedule.service.database.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
@Transactional
public class HibernateSequenceService {

	private EntityManager entityManager;

	@Autowired
	public HibernateSequenceService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Integer getNextVal() {
		Query query = entityManager.createNativeQuery("select nextval('hibernate_sequence')");
		Number nextValNumber = (Number) query.getSingleResult();
		return nextValNumber.intValue();
	}
}
