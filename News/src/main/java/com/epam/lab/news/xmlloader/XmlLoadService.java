package com.epam.lab.news.xmlloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *	Provides scheduling for loading news messages in XML format
 */
@Service
public class XmlLoadService implements ILoadService{

	@Autowired
	private XmlProcessorDispatcher dispatcher;

	@Scheduled(fixedDelayString = "${xmlLoader.repeatTimeout}")
	public void load() {
		
		dispatcher.run();
	}
}
