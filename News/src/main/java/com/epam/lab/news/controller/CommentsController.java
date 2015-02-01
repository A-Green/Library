package com.epam.lab.news.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.lab.news.controller.exception.ControllerException;
import com.epam.lab.news.model.NewsComment;
import com.epam.lab.news.services.comments.ICommentService;
import com.epam.lab.news.services.exception.ServiceException;
import com.fasterxml.jackson.databind.node.ObjectNode;
/**
 * News message comments controller
 */
@Controller
public class CommentsController {
	
	@Autowired
	ICommentService commentService;
	
	/**
	 * Get list of news message comments
	 * @param newsId news message id
	 * @param amount amount of comments to get
	 * @param skip amount of comments to skip 
	 * @return ObjectNode with list of comments and total comments amount 
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/news/{newsId}/comments/", method = RequestMethod.GET, 
					params = {"amount", "skip"},
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	ObjectNode getComments(@PathVariable int newsId, 
						   @RequestParam int amount, 
						   @RequestParam int skip) throws ControllerException {

		try {
			return commentService.getNewsComments(newsId, amount, skip);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
	
	/**
	 * Add comment to news message
	 * @param newsId news id
	 * @param comment comment
	 * @return added news comment
	 * @throws ControllerException
	 */
	@RequestMapping(value = ("/news/{newsId}/comments/"), 
					method = RequestMethod.POST, 
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	NewsComment addNewsComment(@PathVariable int newsId, @RequestBody @Valid NewsComment comment) 
			throws ControllerException {

		comment.setNewsMessageId(newsId);
		
		try {
			commentService.save(comment);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
		
		return comment;
	}
	
	/**
	 * Remove news comment by id
	 * @param id news comment id
	 * @throws ControllerException if comment does not exists 
	 */	
	@RequestMapping(value = "/comments/{commentId}", 
					method = RequestMethod.DELETE, 
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody
	void deleteNewsComment(@PathVariable int commentId) 
			throws ControllerException {

		try {
			commentService.deleteComment(commentId);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
}
