package by.epam.green.library.commands;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.SearhOptions;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.Book;
import by.epam.green.library.model.ClientType;

/**
 * The Class SearchBooksCommand.
 */
public class SearchBooksCommand implements Command {

	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(SearchBooksCommand.class);

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String author = request.getParameter("author");
		
		ArrayList<Book> result = new ArrayList<Book>();
		SearhOptions searhOptions = new SearhOptions();

		try {
			result = searhOptions.extendedSearch(title, genre, author);
		} catch (TechnicalException e) {
			LOGGER.error("Can not search ",e);
			return ResourceManager.getInstance().getParametr("error_page");
		}

		String roleParam = request.getSession().getAttribute("access").toString();

		switch (ClientType.valueOf(roleParam)) {
		
		case READER:
			page = ResourceManager.getInstance().getParametr("catalog_reader_page");
			break;

		case LIBRARIAN:
			page = ResourceManager.getInstance().getParametr("catalog_librarian_page");
			break;
			
		default:
			LOGGER.error("Unknown client role");
			page = ResourceManager.getInstance().getParametr("authorization_page");
		}
		
		request.setAttribute("Books", result);

		return page;
	}
}