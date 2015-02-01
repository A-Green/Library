package com.epam.lab.news.database.dao.tags;

import java.util.List;
import java.util.Map;

import com.epam.lab.news.database.dao.IBaseDAO;
import com.epam.lab.news.model.NewsTag;
/**
 * DAO layer interface for news tags access
 */
public interface INewsTagDAO extends IBaseDAO<NewsTag, String> {

	/**
	 * Get list of all existing news tags
	 * @return list of news tags
	 */
	List<NewsTag> getAllTags();
	
	/**
	 * Get news tags by news message id
	 * @param id news message id
	 * @return list of tags of the news message
	 */
	List<NewsTag> getTagsByNewsId(int id);
	
	/**
	 * Get information about number of news mapped by each tag
	 * @return map, where key is news tag name and value is number of news,
	 * mapped with the tag
	 */
	Map<String, Integer> countNewsByTag();
}
