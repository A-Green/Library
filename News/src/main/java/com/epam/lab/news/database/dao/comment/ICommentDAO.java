package com.epam.lab.news.database.dao.comment;

import java.util.List;

import com.epam.lab.news.database.dao.IBaseDAO;
import com.epam.lab.news.model.NewsComment;

/**
 * DAO layer interface for news comments access
 */
public interface ICommentDAO extends IBaseDAO<NewsComment, Integer> {

	/**
	 * Get list of news message comments
	 * @param newsId news message id
	 * @param amount amount of comments to get
	 * @param skip amount of comments to skip 
	 * @return list of the news message comments
	 */
	List<NewsComment> getNewsCommnets(int newsId, int amount, int skip);
	
	/**
	 * Count news message comments
	 * @param newsId news id
	 * @return number of news message comments
	 */
	long count(int newsId);
}
