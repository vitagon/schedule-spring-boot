package com.vitgon.schedule.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;

@Repository
public interface SchoolDao extends JpaRepository<School, Integer> {
	
	@Query("SELECT s from School s INNER JOIN s.translations st WHERE st.locale = :locale")
	List<School> findAllByLocale(@Param("locale") Locale locale);
	
	School findByUrl(String url);
}
