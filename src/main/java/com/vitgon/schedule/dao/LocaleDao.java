package com.vitgon.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.Locale;

@Repository
public interface LocaleDao extends JpaRepository<Locale, Integer> {
	Locale findByCode(String code);
}
