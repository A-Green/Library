package com.epam.lab.news.services.comments;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lab.news.constants.AppMessages;
import com.epam.lab.news.controller.exception.RESTErrorCode;
import com.epam.lab.news.database.dao.comment.ICommentDAO;
import com.epam.lab.news.model.NewsComment;
import com.epam.lab.news.services.exception.ServiceException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ICommentService implementation
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommentService implements ICommentService {
	
	private static final Logger LOGGER = Logger.getLogger(CommentService.class);

	@Autowired
	private ICommentDAO commentDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ObjectNode getNewsComments(int newsId, int amount, int skip)
			throws ServiceException {
		
		ObjectNode result = new ObjectNode(JsonNodeFactory.instance);
		
		result.put("total", commentDAO.count(newsId));
		result.putPOJO("comments", commentDAO.getNewsCommnets(newsId, amount, skip));
			
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NewsComment save(NewsComment comment) throws ServiceException {
		
		comment.setCreationDate(new Date());
		commentDAO.save((comment));
		return comment;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteComment(int commentId) throws ServiceException {

		NewsComment exists = commentDAO.fetchById(commentId);
		
		if (exists == null) {
			LOGGER.error("Error while deleting comment with id = " + commentId 
					+ AppMessages.ERROR_COMMENT_NOT_FOUND);
			throw new ServiceException(AppMessages.ERROR_COMMENT_NOT_FOUND, RESTErrorCode.INVALID_REQUEST);
		}
		
		commentDAO.delete(exists);
	}
}
