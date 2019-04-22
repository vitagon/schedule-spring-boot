package com.vitgon.schedule.service.database.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HibernateSequenceService {
	
	@Autowired
	private EntityManager entityManager;
	
	public Integer getNextVal() {
		Query query = entityManager.createNativeQuery("select nextval('hibernate_sequence')");
		Number nextValNumber = (Number) query.getSingleResult();
		return nextValNumber.intValue();
	}
}
