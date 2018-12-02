package com.vitgon.schedule.service.base;

import java.util.List;

public interface Service<T, K> {
	T save(T obj);
	T update(T obj);
	T findById(K id);
	List<T> findAll();
}
