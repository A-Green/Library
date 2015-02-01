package com.epam.lab.news.database.dao.authors;

import java.util.List;
import java.util.Map;

import com.epam.lab.news.database.dao.IBaseDAO;
import com.epam.lab.news.model.NewsAuthor;

/**
 * DAO layer interface for news authors access
 */
public interface INewsAuthorDAO extends IBaseDAO<NewsAuthor, Integer> {

	/**
	 * Get list of all existed news authors
	 * @return list of news authors
	 */
	List<NewsAuthor> getAuthors();
	
	/**
	 * Get news authors by news id
	 * @param id news message id
	 * @return list of authors of the news message
	 */
	List<NewsAuthor> getNewsAuthors(int newsId);
	
	/**
	 * Get information about number of news created by each author
	 * @return map, where key is news author name and value is number of news,
	 * created by the author
	 */
	Map<String, Integer> countNewsByAuthors();
	
	/**
	 * Get author by name
	 * @param name author name
	 * @return author
	 */
	NewsAuthor getAuthorByName(String name);
}
