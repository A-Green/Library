package com.epam.lab.news.services;

import com.epam.lab.news.model.ModelObject;
import com.epam.lab.news.services.exception.ServiceException;

/**
 * Service level interface. Provides base service operation 
 * @param <T> manageable entity type
 */
public interface IBaseService<T extends ModelObject> {

	T save(T entity) throws ServiceException;
}
