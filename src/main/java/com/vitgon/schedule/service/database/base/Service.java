package com.vitgon.schedule.service.database.base;

import java.util.List;

public interface Service<T, ID> {
	T save(T obj);
	T update(T obj);
	T findById(ID id);
	List<T> findAll();
	void delete(T obj);
	void deleteById(ID id);
}
