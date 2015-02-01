package com.epam.lab.news.database.dao.news;

import java.util.List;

import com.epam.lab.news.database.dao.IBaseDAO;
import com.epam.lab.news.model.News;

/**
 * DAO layer interface for news messages 
 */
public interface INewsDAO extends IBaseDAO<News, Integer> {
	
	/**
	 * Get news message list
	 * 
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return list of news messages
	 */
	List<News> getList(int amount, int skip);
	
	/**
	 * Get list of news messages mapped by tag
	 * @param tag tag name
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return list of news messages
	 */
	List<News> fetchByTag(String tagName,int amount,int skip);
	
	/**
	 * Get list of news messages by author name
	 * @param author author name
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return list of news messages
	 */
	List<News> fetchByAuthor(String name, int amount, int skip);
	
	/**
	 * Get news messages ordered by comments amount
	 * @param total news messages limit
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return list of news messages
	 */
	List<News> getMostComentedNews(int amount, int skip);

	/**
	 * Count news messages
	 * @return news amount
	 */
	long count();
	
	/**
	 * Count news messages with specified tag 
	 * @return news amount
	 */
	long countByTag(String tag);
	
	/**
	 * Count news messages created by specified author
	 * @return news amount
	 */
	long countByAuthor(String name);	
	
	/**
	 * Updates news message
	 * @param news news message with new news content
	 */
	void update(News news);		
}
