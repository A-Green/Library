package by.epam.green.library.commands;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.CatalogOptions;
import by.epam.green.library.logic.exception.LogicException;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;

/**
 * The Class AddBookCommand.
 */
public class AddBookCommand implements Command {

	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(AddBookCommand.class);

	/**
	* {@inheritDoc}
	* @return add book page or error page if technical error occurred
	*/
	@Override
	public String execute(HttpServletRequest request) {

		String title = request.getParameter("title");
		String authors = request.getParameter("authors");
		String genre = request.getParameter("genre");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");

		CatalogOptions catalogOps = new CatalogOptions();

		try {
			catalogOps.addBook(title, authors, genre, publisher, year);
			request.setAttribute("bookAddingMessage", true);
		} catch (TechnicalException e) {
			LOGGER.error("Can not add book", e);
			return ResourceManager.getInstance().getParametr("error_page");
			
		} catch (LogicException e) {
			request.setAttribute("fillMessage", true);
			return ResourceManager.getInstance().getParametr("add_book_page");
		}

		return ResourceManager.getInstance().getParametr("add_book_page");
	}

}
