package com.epam.lab.news.xmlloader.util;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

/**
 *	Provides methods for searching .xml files
 */
public class FileSearcher {
	
	private static final String FILE_EXTENSION_REGEXP = ".+\\.xml";
	
	private FileSearcher() {		
	}
	
	/**
	 * Recursively scan specified folder and it's subfolders excluding specified errorFolder
	 * and return found files as BlockingQueue
	 * 
	 * @param directoryPath folder to scan in
	 * @param errorFolder excluding folder
	 * @return found file list
	 */
	public static List<File> scan(String directoryPath, String errorFolder) {
			
		List<File> files =  (List<File>) FileUtils.listFiles(
				  new File(directoryPath), 
				  new RegexFileFilter(FILE_EXTENSION_REGEXP), 
						  DirectoryFileFilter.DIRECTORY);

		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).getPath().contains(errorFolder)) {
				files.remove(files.get(i));
				i--;
			}
		}
		
		return files;
	}
}
