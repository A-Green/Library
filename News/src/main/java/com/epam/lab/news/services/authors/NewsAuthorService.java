package com.epam.lab.news.services.authors;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lab.news.constants.AppMessages;
import com.epam.lab.news.controller.exception.RESTErrorCode;
import com.epam.lab.news.database.dao.authors.INewsAuthorDAO;
import com.epam.lab.news.model.NewsAuthor;
import com.epam.lab.news.services.exception.ServiceException;

/**
 * INewsAuthorService implementation
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NewsAuthorService implements INewsAuthorService {
	
	private static final Logger LOGGER = Logger.getLogger(NewsAuthorService.class);
	
	@Autowired
	private INewsAuthorDAO newsAuthorDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<NewsAuthor> getAuthors() {
		return newsAuthorDAO.getAuthors();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Integer> countByAuthors() throws ServiceException {
		return newsAuthorDAO.countNewsByAuthors();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<NewsAuthor> getNewsMessageAuthors(int newsId)
			throws ServiceException {
		return newsAuthorDAO.getNewsAuthors(newsId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NewsAuthor save(NewsAuthor author) throws ServiceException {
		
		NewsAuthor exists = newsAuthorDAO.getAuthorByName(author.getName());
		
		if (exists != null) {
			LOGGER.error("Erorr while adding author. Author with name = " + author.getName() 
					+ "already exists");
			throw new ServiceException(AppMessages.ERROR_ADDING_AUTHOR, RESTErrorCode.INVALID_REQUEST);
		}
		
		newsAuthorDAO.save(author);
		return author;
	}
}
