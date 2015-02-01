package com.epam.lab.news.services.tags;

import java.util.List;
import java.util.Map;

import com.epam.lab.news.model.NewsTag;
import com.epam.lab.news.services.IBaseService;
import com.epam.lab.news.services.exception.ServiceException;
/**
 * Service layer interface for news tags managing
 */
public interface INewsTagService extends IBaseService<NewsTag> {
	
	/**
	 * Get list of all existing news tags
	 * @return list of news tags
	 * @throws ServiceException if can not get news tag list
	 */
	List<NewsTag> getAllTags() throws ServiceException;
	
	/**
	 * Get news tags
	 * @param id news message id
	 * @return list of tags of the news message
	 * @throws ServiceException
	 */
	List<NewsTag> getNewsTags(int newsId) throws ServiceException;
	
	/**
	 * Get information about number of news mapped by each tag
	 * @return map, where key is news tag name and value is number of news,
	 * mapped with the tag
	 * @throws ServiceException
	 */
	Map<String, Integer> countByTags() throws ServiceException;
}
