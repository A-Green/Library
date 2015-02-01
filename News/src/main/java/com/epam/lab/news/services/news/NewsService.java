package com.epam.lab.news.services.news;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lab.news.constants.AppMessages;
import com.epam.lab.news.controller.exception.RESTErrorCode;
import com.epam.lab.news.database.dao.news.INewsDAO;
import com.epam.lab.news.model.News;
import com.epam.lab.news.services.exception.ServiceException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * INewsService implementation
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NewsService implements INewsService {
	
	private static final Logger LOGGER = Logger.getLogger(NewsService.class);
	
	@Autowired
	private INewsDAO newsDAO;
		
	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	@Override
	public ObjectNode getNewsList(int amount, int skip) throws ServiceException {

		ObjectNode result = new ObjectNode(JsonNodeFactory.instance);
		
		result.put("total", newsDAO.count());
		result.putPOJO("news", newsDAO.getList(amount, skip));
			
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	@Override
	public News getNewsMessage(int newsId) throws ServiceException {

		News news = newsDAO.fetchById(newsId);
		
		if (news == null) {
			LOGGER.error("Error while getting news message. News message with id = " + newsId + " not found");
			throw new ServiceException(AppMessages.ERROR_NEWS_MESSAGE_NOT_FOUND,
					RESTErrorCode.RESOURCE_NOT_FOUND);
		}
		
		return news;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public News save(News news) throws ServiceException {

		Date date = new Date();
		news.setCreationDate(date);
		news.setModificationDate(date);
		
		newsDAO.save(news);
		return news;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeNews(int newsId) throws ServiceException {

		News news = newsDAO.fetchById(newsId);
		
		if (news == null) {
			LOGGER.error("Error while deleting news message. News message with id = " + newsId + " not found");
			throw new ServiceException(
					AppMessages.ERROR_NEWS_MESSAGE_NOT_FOUND_WHILE_DELETING,
					RESTErrorCode.INVALID_REQUEST);
		}
		
		newsDAO.delete(news);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateNews(News news) throws ServiceException {	

		news.setModificationDate(new Date());
		newsDAO.update(news);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public ObjectNode getNewsByTag(String tagName, int amount, int skip)
			throws ServiceException {
		
		ObjectNode result = new ObjectNode(JsonNodeFactory.instance);
		
		result.put("total", newsDAO.countByTag(tagName));
		result.putPOJO("news", newsDAO.fetchByTag(tagName, amount, skip));
			
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public ObjectNode getNewsByAuthor(String author, int amount, int skip)
			throws ServiceException {

		ObjectNode result = new ObjectNode(JsonNodeFactory.instance);
		
		result.put("total", newsDAO.countByAuthor(author));
		result.putPOJO("news", newsDAO.fetchByAuthor(author, amount, skip));
			
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public ObjectNode getMostCommentedNews(int amount, int skip, int total)
			throws ServiceException {
		
		if (skip >= total) {
			throw new ServiceException(AppMessages.ERROR_FETCHING_BY_COMMENTS,
					RESTErrorCode.INVALID_REQUEST);
		}
		
		//get part of result, limited by total
		if (skip + amount > total) {
			amount = total - skip;
		}
		
		ObjectNode result = new ObjectNode(JsonNodeFactory.instance);
		
		result.put("total", total);
		result.putPOJO("news", newsDAO.getMostComentedNews(amount, skip));
			
		return result;
	}
}
