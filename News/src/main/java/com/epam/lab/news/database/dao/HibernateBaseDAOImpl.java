package com.epam.lab.news.database.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AbstractDAO implementation based on hibernate framework.
 *
 * @param <T> entity type
 * @param <PK> entity id type
 */
public abstract class HibernateBaseDAOImpl<T, PK extends Serializable> implements IBaseDAO<T, PK > {
	
	@Autowired 
	protected SessionFactory sessionFactory;
	
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public HibernateBaseDAOImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T fetchById(PK id) {
		return (T) sessionFactory.getCurrentSession().get(entityClass, id);
	}

	@Override
	public void save(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
}
