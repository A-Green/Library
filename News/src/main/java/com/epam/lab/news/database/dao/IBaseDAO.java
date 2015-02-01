package com.epam.lab.news.database.dao;

import java.io.Serializable;

/**
 * Interface for base entity management operations
 *
 * @param <T> entity type
 * @param <PK> entity id type
 */
public interface IBaseDAO<T, PK extends Serializable> {

	T fetchById(final PK id);
	void save(final T entity);
	void delete(final T entity);
}
