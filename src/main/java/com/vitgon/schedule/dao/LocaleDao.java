package com.vitgon.schedule.dao;

import com.vitgon.schedule.model.database.Locale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaleDao extends JpaRepository<Locale, Integer> {
	Locale findByCode(String code);
}
