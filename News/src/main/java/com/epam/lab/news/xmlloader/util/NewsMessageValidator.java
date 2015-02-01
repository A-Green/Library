package com.epam.lab.news.xmlloader.util;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.epam.lab.news.constants.AppParameters;
import com.epam.lab.news.model.News;
import com.epam.lab.news.model.NewsAuthor;
import com.epam.lab.news.model.NewsTag;

/**
 * Validates news messages
 */
public class NewsMessageValidator {
	
	private NewsMessageValidator() {		
	}

	public static boolean isValid(List<News> news) {

		for (News newsMessage : news) {

			if (newsMessage.getCreationDate() == null) {
				newsMessage.setCreationDate(new Date(System.currentTimeMillis()));
			}

			if (newsMessage.getModificationDate() == null) {
				newsMessage.setModificationDate(new Date(System.currentTimeMillis()));
			}

			if (!(news != null
					&& checkLength(newsMessage.getTitle(),AppParameters.NEWS_TITLE_MAX_LENGHT)
					&& checkLength(newsMessage.getBrief(),AppParameters.NEWS_BRIEF_MAX_LENGHT)
					&& checkLength(newsMessage.getContent(),AppParameters.NEWS_CONTENT_MAX_LENGHT)
					&& checkAuthors(newsMessage.getAuthors()) 
					&& checkTags(newsMessage.getTags()))) {
				return false;
			}
		}
		
		return true;
	}

	private static boolean checkLength(String param, int maxLength) {

		return param != null && !param.isEmpty() && param.length() <= maxLength;
	}
	
	private static boolean checkAuthors(Set<NewsAuthor> authors) {

		if (authors == null || authors.isEmpty()) {
			return false;
		}

		for (NewsAuthor author : authors) {
			if (author == null || !checkLength(author.getName(), AppParameters.AUTHOR_NAME_MAX_LENGHT)) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean checkTags(Set<NewsTag> tags) {

		if (tags == null || tags.isEmpty()) {
			return false;
		}

		for (NewsTag tag : tags) {
			if (tag == null || !checkLength(tag.getTagName(), AppParameters.TAG_MAX_LENGHT)) {
				return false;
			}
		}
		
		return true;
	}
}
