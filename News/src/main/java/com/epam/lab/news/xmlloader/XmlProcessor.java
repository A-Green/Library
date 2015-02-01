package com.epam.lab.news.xmlloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Callable;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lab.news.database.dao.news.INewsDAO;
import com.epam.lab.news.model.News;
import com.epam.lab.news.model.NewsList;
import com.epam.lab.news.xmlloader.util.NewsMessageValidator;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 *	Process specified files. Performs saving and deleting file in case if it is valid
 *	and moving to error folder otherwise 
 */
@Configurable
public class XmlProcessor implements Callable<Boolean> {

	private static final Logger LOGGER = Logger.getLogger(XmlProcessor.class);
	
	private static final XmlMapper xmlMapper =  new XmlMapper(new JacksonXmlModule());

	private File file;
	
	@Value(value = "${xmlLoader.errorFolderPath}")
	private String errorFolderPath;

	@Autowired
	private INewsDAO newsDAO;

	public XmlProcessor(File file) {
		this.file = file;
	}

	@Override
	public Boolean call() throws Exception {
		
		boolean succsess = true;

		try {
			
			LOGGER.debug("Thread " + Thread.currentThread().getName() + " executes " + file.getName());
			
			NewsList newsList = xmlMapper.readValue(FileUtils.readFileToString(file), NewsList.class);

			if (NewsMessageValidator.isValid(newsList.getNews())) {

				for (News news : newsList.getNews()) {
					saveNews(news);
				}

				Files.delete(file.toPath());

			} else {
				LOGGER.error("News from file " + file.getName() + " are not valid");
				moveToErrorFolder(file);
				succsess = false;
			}

		} catch (DataAccessException e) {
			LOGGER.error("One or more news from file " + file.getName() + " are not loaded. Reason: " + e.getMessage());
			moveToErrorFolder(file);
			succsess = false;
		} catch (Exception e) {
			LOGGER.error("Can not process file " + file.getName() + ". Reason: " + e.getMessage());
			moveToErrorFolder(file);
			succsess = false;
		}
		
		return succsess;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void saveNews(News news) {
			newsDAO.save(news);
	}

	private void moveToErrorFolder(File processFile) {

		try {

			Files.move(	processFile.toPath(),
						new File(errorFolderPath + processFile.getName()).toPath(),
						StandardCopyOption.ATOMIC_MOVE);
			
		} catch (IOException e) {
			LOGGER.error("Can not move file " + processFile.getName() + "to error folder. Reason: " + e.getMessage());
		}
	}
}
