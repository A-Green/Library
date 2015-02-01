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
import com.epam.lab.news.model.News;
import com.epam.lab.news.services.exception.ServiceException;
import com.epam.lab.news.services.news.INewsService;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 *	Based on REST services controller for 
 *	news resources managing
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private INewsService newsService;
	
	/**
	 * Get news messages list
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return ObejctNode with requested news and information about total number of 
	 * news messages
	 * @throws ControllerException 
	 */
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET, 
					params = {"amount", "skip"},
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody	
	ObjectNode getNewsList(@RequestParam int amount, @RequestParam int skip) 
			throws ControllerException {
		
		try {
			return newsService.getNewsList(amount, skip);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
	
	/**
	 * Get news messages by tag
	 * @param tag tag name
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return ObejctNode with requested news and information about total number of 
	 * news messages with the tag
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, 
					params = {"tag", "amount", "skip"},
					headers = { "Accept=application/json, text/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	ObjectNode getNewsByTag(@RequestParam String tag, @RequestParam int amount,
			@RequestParam int skip) throws ControllerException {
		
		try {
			return newsService.getNewsByTag(tag, amount, skip);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
	
	/**
	 * Get news messages by author name
	 * @param author author name
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return ObejctNode with requested news and information about total number of 
	 * news messages created by the author
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, 
			params = {"author", "amount", "skip" }, 
			headers = { "Accept=application/json, text/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	ObjectNode getNewsByAuthor(@RequestParam String author,
			@RequestParam int amount, @RequestParam int skip)
			throws ControllerException {

		try {
			return newsService.getNewsByAuthor(author, amount, skip);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
	
	/**
	 * Get news messages ordered by comments amount
	 * @param total news messages limit
	 * @param amount number of news to load
	 * @param skip number of news to skip
	 * @return ObejctNode with requested news and information about limit
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/commented", method = RequestMethod.GET, 
					params = {"total", "amount", "skip"}, 
					headers = { "Accept=application/json, text/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	ObjectNode getMostCommentedNews(@RequestParam int total, @RequestParam int amount, @RequestParam int skip)
			throws ControllerException {
		
		try {
			return newsService.getMostCommentedNews(amount, skip, total);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
	
	/**
	 * Get news message by id
	 * @param id news message id
	 * @return news message
	 * @throws ControllerException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, 
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	News showNewsMessage(@PathVariable int id) throws ControllerException {

		try {
			return newsService.getNewsMessage(id);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
	
	/**
	 * Create new news message
	 * @param newsMessage new news message
	 * @return created news message
	 * @throws ControllerException
	 */
	@RequestMapping(value = ("/"), method = RequestMethod.POST, 
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	News addNewsMessage(@Valid @RequestBody News newsMessage) 
			throws ControllerException {
		
		try {
			newsService.save(newsMessage);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
		
		return newsMessage;
	}
	
	/**
	 * Update news message
	 * @param id news message id
	 * @param news news message with new news content
	 * @return updated news message
	 * @throws ControllerException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, 
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	News updateNewsMessage(@PathVariable int id, @Valid @RequestBody News news) 
			throws ControllerException {
		
		news.setId(id);
		
		try {
			newsService.updateNews(news);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}

		return news;
	}
	
	/**
	 * Delete news message by id
	 * @param id news id
	 */
	@RequestMapping(value = "/{id}", 
					method = RequestMethod.DELETE, 
					headers = { "Accept=application/json, text/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody
	void deleteNewsMessage(@PathVariable int id) throws ControllerException {

		try {
			newsService.removeNews(id);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
}
