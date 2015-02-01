package com.epam.lab.news.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.lab.news.controller.exception.ControllerException;
import com.epam.lab.news.model.NewsAuthor;
import com.epam.lab.news.services.authors.INewsAuthorService;
import com.epam.lab.news.services.exception.ServiceException;
/**
 * News authors controller
 */
@Controller
public class NewsAuthorsController {

	@Autowired
	INewsAuthorService newsAuthorService;
	
	/**
	 * Get list of all existed news authors
	 * @return list of news authors
	 * @throws ControllerException 
	 */
	@RequestMapping(value = {"authors/", "authors"},
					method = RequestMethod.GET, 
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<NewsAuthor> getAuthors() throws ControllerException {

		List<NewsAuthor> authors = null;
		
		try {
			authors = newsAuthorService.getAuthors();
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
		
		return authors;
	}
	
	/**
	 * Get information about number of news created by each author
	 * @return map, where key is news author name and value is number of news,
	 * created by the author
	 * @throws ControllerException
	 */
	@RequestMapping(value = "authors/list", 
					method = RequestMethod.GET, 
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Map<String, Integer> count() throws ControllerException {
		
		Map<String, Integer> result = null;
		
		try {
			result = newsAuthorService.countByAuthors();
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
		
		return result;
	}
	
	/**
	 * Get news authors
	 * @param id news message id
	 * @return list of authors of the news message
	 * @throws ControllerException
	 */
	@RequestMapping(value = "news/{id}/authors/", 
					method = RequestMethod.GET, 
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<NewsAuthor> getNewsAuthors(@PathVariable int id) throws ControllerException {
		
		List<NewsAuthor> result = null;
		
		try {
			result = newsAuthorService.getNewsMessageAuthors(id);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
		
		return result;
	}
	
	/**
	 * Add new author
	 * @param author new author
	 * @return author id
	 * @throws ControllerException
	 */
	@RequestMapping(value = "authors/", 
			method = RequestMethod.POST, 
			headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	int addAuthor(@RequestBody @Valid NewsAuthor author) throws ControllerException {
		
		try {
			newsAuthorService.save(author);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
		
		return author.getId();
	}
}
