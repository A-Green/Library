package com.epam.lab.news.services.authors;

import java.util.List;
import java.util.Map;

import com.epam.lab.news.controller.exception.ControllerException;
import com.epam.lab.news.model.NewsAuthor;
import com.epam.lab.news.services.IBaseService;
import com.epam.lab.news.services.exception.ServiceException;

/**
 * Service layer interface for news authors managing
 */
public interface INewsAuthorService  extends IBaseService<NewsAuthor> {
	
	/**
	 * Get list of all existed news authors
	 * @return list of news authors
	 * @throws ControllerException 
	 */
	List<NewsAuthor> getAuthors() throws ServiceException;
	
	/**
	 * Get news authors
	 * @param id news message id
	 * @return list of authors of the news message
	 * @throws ControllerException
	 */
	List<NewsAuthor> getNewsMessageAuthors(int newsId) throws ServiceException;
	
	/**
	 * Get information about number of news created by each author
	 * @return map, where key is news author name and value is number of news,
	 * created by the author
	 * @throws ControllerException
	 */
	Map<String, Integer> countByAuthors() throws ServiceException;
}
