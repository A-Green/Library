package com.epam.lab.news.services.tags;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lab.news.constants.AppMessages;
import com.epam.lab.news.controller.exception.RESTErrorCode;
import com.epam.lab.news.database.dao.tags.INewsTagDAO;
import com.epam.lab.news.model.NewsTag;
import com.epam.lab.news.services.exception.ServiceException;

/**
 * INewsTagService implementation
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NewsTagService implements INewsTagService {
	
	private static final Logger LOGGER = Logger.getLogger(NewsTagService.class);

	@Autowired
	INewsTagDAO newsTagDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<NewsTag> getAllTags() throws ServiceException {
		return newsTagDAO.getAllTags();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Map<String, Integer> countByTags() throws ServiceException {
		return newsTagDAO.countNewsByTag();	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<NewsTag> getNewsTags(int newsId) throws ServiceException {		
		return newsTagDAO.getTagsByNewsId(newsId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NewsTag save(NewsTag tag) throws ServiceException {
		
		NewsTag exists = newsTagDAO.fetchById((tag.getTagName()));
		
		if (exists != null) {
			LOGGER.error(AppMessages.ERROR_ADDING_TAG);
			throw new ServiceException(AppMessages.ERROR_ADDING_TAG, RESTErrorCode.INVALID_REQUEST);
		}
		
		newsTagDAO.save(tag);
		return tag;
	}

}
