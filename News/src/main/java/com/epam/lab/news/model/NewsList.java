package com.epam.lab.news.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "newsMessages")
public class NewsList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<News> news;

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}
}
