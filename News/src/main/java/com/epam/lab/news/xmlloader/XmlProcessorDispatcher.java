package com.epam.lab.news.xmlloader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epam.lab.news.database.dao.news.INewsDAO;
import com.epam.lab.news.xmlloader.util.FileSearcher;

/**
 * XML processor dispatcher. Manages xml processors threads.
 */
@Component
public class XmlProcessorDispatcher {

	private static final Logger LOGGER = Logger.getLogger(XmlProcessorDispatcher.class);

	@Value(value = "${xmlLoader.threadPoolSize}")
	private int threadPoolSize;

	@Value(value = "${xmlLoader.scanFolder}")
	private String scanFolder;

	@Value(value = "${xmlLoader.executionWaitTimeout}")
	private int waitTimeout;

	@Value(value = "${xmlLoader.errorFolderPath}")
	private String errorFolder;

	@Autowired
	private INewsDAO newsDAO;

	private ExecutorService executorService;

	@PostConstruct
	private void initPool() {

		executorService = Executors.newFixedThreadPool(threadPoolSize);
	}

	public void run() {

		List<File> files = FileSearcher.scan(scanFolder, errorFolder);
		
		if (files.isEmpty()) {
			LOGGER.debug("No files found");
			return;
		}
		
		List<XmlProcessor> tasks = new ArrayList<XmlProcessor>(files.size());
		
		for (File file : files) {
			tasks.add(new XmlProcessor(file));
		}

		try {
			executorService.invokeAll(tasks);
		} catch (InterruptedException e) {
			LOGGER.error("Error xml processors invokation. " + e.getMessage());
		}

	}
	
	@PreDestroy
	private void destroyPool() {

		executorService.shutdown();

		try {
			
			if (!executorService.awaitTermination(waitTimeout, TimeUnit.MILLISECONDS)) {
				LOGGER.error("Executor service timeout elapsed before termination");
			}
			
		} catch (InterruptedException e) {
			LOGGER.error("Executor service shutdown error. " + e.getMessage());
		}
	}
}
