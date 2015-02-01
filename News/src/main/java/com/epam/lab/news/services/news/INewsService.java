package com.epam.lab.news.services.news;

import com.epam.lab.news.model.News;
import com.epam.lab.news.services.IBaseService;
import com.epam.lab.news.services.exception.ServiceException;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Service layer interface for news content managing
 */
public interface INewsService extends IBaseService<News> {

	/**
	 * Get news messages ordered by comments amount
	 * @param total news messages limit
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return ObejctNode with requested news and information about limit
	 * @throws ServiceException
	 */
	ObjectNode getMostCommentedNews(int amount, int skip, int total) throws ServiceException;
	
	/**
	 * Get news messages list
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return ObejctNode with requested news and information about total number of 
	 * news messages
	 * @throws ServiceException 
	 */
	ObjectNode getNewsList(int amount, int skip) throws ServiceException;
	
	/**
	 * Get news messages by tag
	 * @param tag tag name
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return ObejctNode with requested news and information about total number of 
	 * news messages with the tag
	 * @throws ServiceException
	 */
	ObjectNode getNewsByTag(String tagName, int amount, int skip) throws ServiceException;
	
	/**
	 * Get news messages by author name
	 * @param author author name
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return ObejctNode with requested news and information about total number of 
	 * news messages created by the author
	 * @throws ServiceException
	 */
	ObjectNode getNewsByAuthor(String author, int amount, int skip) throws ServiceException;
	
	/**
	 * Get news message by id
	 * @param id news message id
	 * @return news message
	 * @throws ServiceException 
	 */
	News getNewsMessage(int newsId) throws ServiceException;
	
	/**
	 * Delete news message by id
	 * @param id news id
	 */
	void removeNews(int newsId) throws ServiceException;
	
	/**
	 * Update news message
	 * @param id news message id
	 * @param news news message with new news content
	 * @return updated news message
	 * @throws ServiceException 
	 */
	void updateNews(News news) throws ServiceException;
}
