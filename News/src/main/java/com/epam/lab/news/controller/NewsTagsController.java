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
import com.epam.lab.news.model.NewsTag;
import com.epam.lab.news.services.exception.ServiceException;
import com.epam.lab.news.services.tags.INewsTagService;

@Controller
public class NewsTagsController {
	
	@Autowired
	INewsTagService newsTagService;
	
	/**
	 * Get list of all existing news tags
	 * @return list of news tags
	 * @throws ControllerException if can not get news tag list
	 */
	@RequestMapping(value = { "tags/", "tags" }, 
					method = RequestMethod.GET, 
					headers = { "Accept=application/json, text/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<NewsTag> getTagList() throws ControllerException {

		try {
			return newsTagService.getAllTags();
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
	
	/**
	 * Get information about number of news mapped by each tag
	 * @return map, where key is news tag name and value is number of news,
	 * mapped with the tag
	 * @throws ControllerException
	 */
	@RequestMapping(value = "tags/list", 
					method = RequestMethod.GET, 
					headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Map<String, Integer> count() throws ControllerException {
				
		try {
			return newsTagService.countByTags();
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
	
	/**
	 * Get news tags
	 * @param id news message id
	 * @return list of tags of the news message
	 * @throws ControllerException
	 */
	@RequestMapping(value = "news/{id}/tags/", 
			method = RequestMethod.GET, 
			headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<NewsTag> getNewsTags(@PathVariable int id) throws ControllerException {
		
		try {
			return newsTagService.getNewsTags(id);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
	
	/**
	 * Add new tag
	 * @param tag new tag
	 * @throws ControllerException
	 */
	@RequestMapping(value = "tags/", 
			method = RequestMethod.POST, 
			headers = {"Accept=application/json, text/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	void addNewTag(@Valid @RequestBody NewsTag tag) throws ControllerException {

		try {
			newsTagService.save(tag);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e.getErrorCode());
		}
	}
}
