package com.epam.lab.news.services.comments;

import com.epam.lab.news.model.NewsComment;
import com.epam.lab.news.services.IBaseService;
import com.epam.lab.news.services.exception.ServiceException;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Service layer interface for news comments managing
 */
public interface ICommentService extends IBaseService<NewsComment>  {

	/**
	 * Get list of news message comments
	 * @param newsId news message id
	 * @param amount amount of comments to get
	 * @param skip amount of comments to skip 
	 * @return ObjectNode with list of comments and total comments amount 
	 * @throws ServiceException
	 */
	ObjectNode getNewsComments(int newsId, int amount, int skip) throws ServiceException;
	
	/**
	 * Remove news comment by id
	 * @param id news comment id
	 * @throws ServiceException
	 */	
	void deleteComment(int commentId) throws ServiceException;
}
