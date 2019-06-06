package com.vitgon.schedule.service.database.base;

import java.util.List;
import java.util.Optional;

public interface Service<T, ID> {
	T save(T obj);
	T update(T obj);
	Optional<T> findById(ID id);
	List<T> findAll();
	void delete(T obj);
	void deleteById(ID id);
}
